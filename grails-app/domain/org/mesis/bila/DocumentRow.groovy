package org.mesis.bila

class DocumentRow  implements Comparable<DocumentRow>{

	static constraints = {
		account(nullable: false)
		debit(nullable: false)
		amount(nullable: false, min: 0.01d, scale: 2)
		note(nullable: true)
	}

	static belongsTo = [document:Document]

	int rowNr = 0
	org.mesis.bila.Account account
	Boolean debit = true
	Double amount = 0
	String note
//	Date dateCreated
//	Date lastUpdated

//	static transients = ['dare', 'avere']
	
//    static mapping= {
//		autoTimestamp true
//		dare  formula("case when ((debit=true and amount>=0) or (debit=false and amount<0)) then amount else 0 end")
//		avere formula("case when ((debit=false and amount>0) or (debit=true and amount<0)) then amount else 0 end")
//    }
	
	/**
	* @return importo dare mai negativo
	*/
	Double getDare() {
		return (debit && amount>=0) || (!debit && amount<0) ? amount.abs() : 0
	}
	
	/**
	* @return importo avere mai negativo
	*/
	Double getAvere() {
		return (!debit && amount>0) || (debit && amount<0) ? amount.abs() : 0
	}
	
	def beforeInsert() {
      if (rowNr == 0) {
         rowNr = 1	//TODO: calcolare la riga max 
      }
	}
	
@Override
	public int compareTo(DocumentRow o) {
		int c = document <=> o.document
		if (c != 0)
			return c
			
		c = rowNr <=> o.rowNr
		if (c != 0)
			return c
			
		return id <=> o.id	
	}
	

	String toString(){
		"${account} - ${amount} ${note}"
	}

}
