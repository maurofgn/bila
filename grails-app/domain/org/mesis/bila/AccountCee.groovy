package org.mesis.bila

import org.mesis.bila.NodeType;

class AccountCee implements Comparable<AccountCee>{

	String code
	String description
	boolean active = true
	int seqNo = 0
	org.mesis.bila.AccountCee parent
	boolean summary = false
	boolean debit = true
	boolean total = false
	NodeType nodeType
//	Date dateCreated
//	Date lastUpdated
	SortedSet sons
	
	int level = 0
	CreditDebit creditDebit = null

//	static belongsTo = [parent: AccountCee]
	
	static hasMany = [sons: AccountCee, accounts:Account]
//	static mappedBy = [sons: 'parent']
	
	static transients = ['level', 'creditDebit']
	
//	static mapping = {
//		autoTimestamp true
//	}
	
	static constraints = {
		code(maxSize:10, nullable: false, blank: false, unique: true)		
		description(maxSize:60, nullable: false, blank: false)
		active(nullable: false)
		seqNo(maxSize:10, nullable: false)
		
		parent(nullable: true, 
			validator: {val, obj-> if (!val && obj.nodeType != NodeType.ROOT) 'parent mandatory'}
			)
			
		summary(nullable: false)
		debit(nullable: false)
		total(nullable: false)
		nodeType(nullable: true, display: false)
	}
	
	/**
	* @return lista di accountCee di tipo summary e attivi
	*/
	static List<AccountCee> parentList() {
	
		return AccountCee.createCriteria().list{
			eq('summary', true)
			eq('active', true)
		}
	}
	
	/**
	*
	* @return elenco dei conti: Attivo, Passico, Costi, Ricavi
	*/
	static List<AccountCee> accountsAPCR() {
	
		return AccountCee.createCriteria().list {
			and {
				isNotNull("nodeType") 
				eq("summary", true)
				}
			and {        
				order("seqNo", "asc")
				order("code", "asc")
				}
			}
	}
	
	static AccountCee getNodeFromType(NodeType nodeType) {
		return nodeType ? AccountCee.findByNodeType(nodeType) : null
	}
	
	/**
	* @return true se il conto è dare
	*/
	public boolean getAncestorDebit() {
		return getAncestor().debit
	}
	
	/**
	*
	* @return lista di conti cee (summary compresi) con il valore da documenti CreditDebit
	*/
	List<BilaRow> getBalance(int year) {
		def retValue = []

		List<AccountCee> allSons = getAllSons()
		
		allSons.each { c -> 
			CreditDebit cd = c.creditDebit(year)
//			println("conto: ${c} saldo: ${c.creditDebit}")
			BilaRow br = new BilaRow(accountCee: c, creditDebit: cd, 
				nodeType: this.nodeType, level: c.level, code:c.code, description:c.description, summary:c.summary, total:c.total,
				amountYear:cd.balanceYear, amountYearPre:cd.balanceYearPrev, amountDelta: cd.balanceYear-cd.balanceYearPrev
				);
			retValue << br
			
			List<AccountCee> parentsTotal = c.getParentsTotal(this)	//righe totali
			
			parentsTotal.each{ pt ->
				cd = pt.creditDebit
				br = new BilaRow(accountCee: pt, creditDebit: cd, 
					nodeType: this.nodeType, level: pt.level, code:pt.code, description:pt.description, summary:pt.summary, total:pt.total
					,amountYear:cd.balanceYear, amountYearPre:cd.balanceYearPrev, amountDelta: cd?.balanceYear-cd?.balanceYearPrev
					);
				retValue << br
			}
		}
		
		retValue.each { br -> 
			CreditDebit cd = br.creditDebit
			if (cd) {
				br.amountYear = cd.balanceYear
				br.amountYearPre = cd.balanceYearPrev
				br.amountDelta = cd.balanceYear-cd.balanceYearPrev
			}
		}
		
		return retValue
	}
	
	boolean isLastBrother() {
		return !parent || nodeType || parent.sons.last() == this
	}
	
	/**
	* definisce il totale dare e avere per anno e anno-1
	*/
	def creditDebit(int year) {
		
		creditDebit = new CreditDebit(year: year)
		//conti del piano dei conti appartenenti a questo conto cee
		accounts?.each { pc -> creditDebit.add(pc.creditDebit(year)) }
		
		if (parent && !creditDebit.isEmpty()) {
			log.info "${this.toString()} ${parent.toString()}"
			addOnParents(parent, creditDebit)
		}
		return creditDebit
	}
	
	/**
	*
	*accumulu sui parent
	*/
	def addOnParents(AccountCee c = null, CreditDebit cd) {
		if (c && cd && c.creditDebit && !cd.isEmpty()) {
			c.creditDebit.add(cd)
			addOnParents(c.parent, cd) 
		}
	}
	
	/**
	* Lista di nodi ottenuta dall'attraversamento dell'albero
	* @return lista con tutti i figli a tutti i livelli del nodo corrente
	*/
	List<AccountCee> getAllSons() {
	
		def allNode = []	//new ArrayList()
		def traverse
		traverse = { node, level=0 ->

			if (node) {
			   // println "${'|  ' * arg}- ${node.getDescription()}\\"
				node.level = level
				allNode.add(node)
				
				if (node.sons)
					node.sons.each {oneSon -> traverse(oneSon, (level+1)) }
			}
		}
	
		traverse(this)
		return allNode
	}
	
	
	/**
	* solo x foglie e ultimo di un gruppo di fratelli si verifica il parent e vengono inclusi i parent di tipo total
	* @param maxParent ultimo nodo (compreso) dei parent. Nodo oltre il quale non si deve andare
	* @return lista dei parent per cui va fatto il totale
	*/
	def getParentsTotal(AccountCee maxParent = null) {

		def allParent = []    //new ArrayList()

		if (isLeaf() && isLastBrother()) {
			
			def climb
			climb = { node, level = -1 ->
				if (node) {
					if (node.total) {
						node.level = level
						allParent.add(node)
					}
					if (node.isLastBrother() && (  (maxParent?.id?:0) != (node?.id?:0) ))
						climb(node.parent, (level-1))
				}
			}
			climb(parent)
		}
		return allParent
	}
	
	/**
	* @return Lista di tutti i nodi parent fino a root
	*/
	List<AccountCee> getAllParents() {
	
		def allParent = []	//new ArrayList()
		def climb
		climb = {AccountCee node ->

			if (node) {
				allParent.add(node)
				if (node.parent)
					climb(node.parent)
			}
		}
	
		climb(parent)
		return allParent
	}
	
	/**
	*
	* @return i conti del piano dei conti agganciati ad un conto cee figlio a qualsiasi livello del corrente
	*/
	List<Account> getAllAccount() {
		def pc = []
		allSons?.each { b -> 
			if (!b.sons && b.accounts) {
				pc += b.accounts
			}
		}
		return pc
	}
	
	/**
	* @return il primo conto avo con docType definito (attivo, passivo, costo, ricavo)
	*/
	public AccountCee getAncestor() {
		def currentNode = this;
		while (currentNode.parent != null && !currentNode.nodeType) {
		   currentNode = currentNode.parent
		}
		return currentNode
	}
	
	AccountCee getRootNode(){
       if(parent){
          //if parent is not null then by definition this node is a child node of the tree.
          return parent.getRootNode()
       }else{
          //if parent is null then by definition it is the root node.
          return this
       }
    }

	//you might not need this function, but ill add it as it is common in tree structures
	boolean isLeaf(){
		//determines if this node is a leaf node. a leaf is a node with zero childrens
		return sons.isEmpty()
	}
	
	@Override
	public int compareTo(AccountCee o) {
		return seqNo <=> o.seqNo
	}
	
	String toString(){
		"${code} - ${description}"
	}
}
