package org.mesis.bila

import grails.transaction.Transactional

import org.mesis.bila.*

class BilaService {

//@Transactional
//    def serviceMethod() {
//
//    }


//	/**
//	* restituisce il totale dare e avere per anno e anno-1 del conto
//	*/
//	CreditDebit creditDebit(int year, Account account) {
//		CreditDebit retValue = new CreditDebit(year: year)
//		account?.documentRows?.each { r ->
//			retValue.addRow(r)
//        }
//		return retValue
//	}

	/**
	* Lista di nodi ottenuta dall'attraversamento dell'albero
	* @param nodeType tipo nodo di partenza
	*/
	List<AccountCee> getNodesList(NodeType nodeType = NodeType.ROOT) {
	
		def allNode
		def traverse
		
		traverse = { node, level=0 ->

			if (node) {
				if (level == 0)
					allNode = []	//new ArrayList()
			   // println "${'|  ' * arg}- ${node.getDescription()}\\"
				node.level = level
				allNode.add(node)
				
				if (node.sons)
					node.sons.each {oneSon -> traverse(oneSon, (level+1)) }
				
			}
		}
	
		def nodeRoot = AccountCee.findByNodeType(nodeType)
		traverse(nodeRoot)
		return allNode
	}
	
	/**
	* @return carica le righe 
	*/
    def loadBila(NodeType nodeType = NodeType.ROOT, int year) {
		
        def bilaRowList = []
        def bilaRow
		List<AccountCee> accountCeeList = getNodesList(nodeType)

		accountCeeList.each{ conto ->
//			if (!conto.sons)
				
		}
		
        for(int i = 1; i <= counts; i++) {
            bilaRow = new BilaRow(
				code: "name" + i,
				description: i,
				summary: true,
				amountYear: 150,
				amountYearPre: 120,
				amountDelta: 150 - 120,
				nodeType: nodeType,
				rowLevel: 1
				)
				
            bilaRowList.add(bilaRow)
        }
        return bilaRowList
    }
	
	def loadMockDoc(int loopNr = 0) {
//		if (Document.count())
//			return;	//già caricato
		
		def dts = DocType.list()	//tipi documento
		if (!dts || dts.isEmpty())
			return

		def attivo = AccountCee.getNodeFromType(NodeType.ATTIVO).getAllAccount()		//dare
		if (!attivo || attivo.isEmpty())
			return
		def passivo = AccountCee.getNodeFromType(NodeType.PASSIVO).getAllAccount()		//avere
		if (!passivo || passivo.isEmpty())
			return
		
		def costi = AccountCee.getNodeFromType(NodeType.COSTI).getAllAccount()			//dare
		if (!costi || costi.isEmpty())
			return
		def ricavi = AccountCee.getNodeFromType(NodeType.RICAVI).getAllAccount()		//avere
		if (!ricavi || ricavi.isEmpty())
			return

		int attivoIndex = 0
		int passivoIndex = 0
		int costiIndex = 0
		int ricaviIndex = 0
		int docNo = 0
		
		def loop
		
		loop = { cicleNr, dateReg, delta=0 ->
        
			//println("ciclo ddd $cicleNr")
		
			int year = dateReg.get(GregorianCalendar.YEAR)
		 
			dts.eachWithIndex  { dt, index ->
				
				boolean even = index % 2
				docNo++
				def Document doc = new Document(docType: dt, dateReg: dateReg.time, dateDoc: dateReg.time, nrDoc: docNo, note:"nota ${docNo}", amount: (even ? 30+delta : 50+delta))
				
				if (even) {
					doc.addToRows(new DocumentRow(rowNr: 1, account: attivo[attivoIndex%attivo.size], debit: true, amount: 30+delta, note: "attivo dare"))
					doc.addToRows(new DocumentRow(rowNr: 2, account: ricavi[ricaviIndex%ricavi.size], debit: false, amount: 20+delta, note: "ricavo avere"))
					doc.addToRows(new DocumentRow(rowNr: 3, account: passivo[passivoIndex%passivo.size], debit: false, amount: 10, note: "passivo avere"))
					ricaviIndex++
					attivoIndex++
					passivoIndex++
				} else {
					doc.addToRows(new DocumentRow(rowNr: 1, account: passivo[passivoIndex%passivo.size], debit: false, amount: 50+delta, note: "passivo avere"))
					doc.addToRows(new DocumentRow(rowNr: 2, account: costi[costiIndex%costi.size], debit: true, amount: 45+delta, note: "costo dare"))
					doc.addToRows(new DocumentRow(rowNr: 3, account: attivo[attivoIndex%attivo.size], debit: true, amount: 5, note: "attivo dare"))
					costiIndex++
					attivoIndex++
					passivoIndex++
				}
				
				doc.save(flush:true, failOnError: true)
			}
		}
		
		
		def d = new GregorianCalendar()
		for (int i = 0; i < loopNr; i++) {
			loop(i, d)
		}
		
		docNo = 0
		d.add(GregorianCalendar.YEAR, -1)
		for (int i = 0; i < loopNr; i++) {
			loop(i, d, -2)
		}
		
	}
	
	/**
	* carico l'anagrafica avis
	*/
	def loadAvis() {
		if (Avis.count())
			return;	//già caricato
			
		new Avis(name: "Comunale Morrovalle", 
			address: "piazza Vittorio Emanuele II n.12", city: "Morrovalle",
			postalCode: 62010,
			region: "MC",
			email: "morrovalle.comunale@avit.it",
			phone: "0733/222405",
			contactName: "Dott. Signorini"
		).save(flush:true, failOnError: true)
	}	

	/**
	* carico tipi doc. standard
	*/
	def loadDocType() {
		if (DocType.count())
			return;	//già caricato
			
		new DocType(code: "acqFat", description: "Fattura di Acquisto").save(flush:true, failOnError: true)
		new DocType(code: "acqRic", description: "Ricevuta di Acquisto").save(flush:true, failOnError: true)
		
		new DocType(code: "venFat", description: "Fattura di Vendita").save(flush:true, failOnError: true)
		new DocType(code: "venRic", description: "Ricevuta di Vendita").save(flush:true, failOnError: true)
		
		new DocType(code: "cassaUscita", description: "Uscita di cassa").save(flush:true, failOnError: true)
		new DocType(code: "cassaEntrata", description: "Entrata di cassa").save(flush:true, failOnError: true)
		
		new DocType(code: "bancaAddebito", description: "Addebito bancario").save(flush:true, failOnError: true)
		new DocType(code: "bancaAccredito", description: "Accredito bancario").save(flush:true, failOnError: true)
		
		new DocType(code: "giro", description: "Giroconti").save(flush:true, failOnError: true)
	}
  
	/**
	* carico elementi del piano dei conti dai conti del bilancio
	*/
	def loadPC() {
		if (Account.count())
			return;	//già caricato
	
		List<AccountCee> allNode = getNodesList()

		allNode.each { node ->
			if (!node.summary && !node.nodeType) {
				new Account(code: node.getCode(), description: node.getDescription(), accountCee: node)
					.save(flush:true, failOnError: true)
				}
			}
	}
	
	def loadBilaCee() {
	
		if (AccountCee.count())
			return;	//già caricato
	
		AccountCee root = new AccountCee(code: "Root", description: "Root", seqNo: 0, summary:false, total:false, nodeType:NodeType.ROOT)
				
		AccountCee patri = new AccountCee(code: "Patri", description: "Patrimoniale", seqNo: 10, summary:false, total:true, nodeType:NodeType.PATRIMONIALE, parent:root)
		root.addToSons(patri)
		
		AccountCee econo = new AccountCee(code: "Econo", description: "Economico", seqNo: 20, summary:false, total:true, nodeType:NodeType.ECONOMICO, parent:root)
		root.addToSons(econo)
		
		AccountCee attivo = new AccountCee(code: "Attivo", description: "Attivo", seqNo: 10, summary:true, total:true, nodeType:NodeType.ATTIVO, debit:true, parent:patri)
		AccountCee passivo = new AccountCee(code: "Passivo", description: "Passivo", seqNo: 20, summary:true, total:true, nodeType:NodeType.PASSIVO, debit:false, parent:patri)
		patri.addToSons(attivo)
		patri.addToSons(passivo)
		
		AccountCee costi = new AccountCee(code: "Costi", description: "Costi", seqNo: 30, summary:true, total:true, nodeType:NodeType.COSTI, debit:true, parent:econo)
		AccountCee ricavi = new AccountCee(code: "Ricavi", description: "Ricavi", seqNo: 40, summary:true, total:true, nodeType:NodeType.RICAVI, debit:false, parent:econo)
		econo.addToSons(costi)
		econo.addToSons(ricavi)
		root.save(flush:true, failOnError: true);
		
//attivo
		AccountCee a = new AccountCee(code: "A", description: "Crediti verso soci per versamento quote", seqNo: 10, summary:true, total:true, debit:true, parent:attivo)
		attivo.addToSons(a)
		
		AccountCee b = new AccountCee(code: "B", description: "Immobilizzazioni", seqNo: 20, summary:true, total:true, debit:true, parent:attivo)
		attivo.addToSons(b)
		
		AccountCee b1 = new AccountCee(code: "B1", description: "Immobilizzazioni immateriali", seqNo: 10, summary:true, total:false, debit:true, parent:b)
		b.addToSons(b1)
		b1.addToSons(new AccountCee(code: "B11", description: "Spese costituzione e modifiche statutarie", seqNo: 10, summary:false, total:false, debit:true, parent:b1))
		b1.addToSons(new AccountCee(code: "B12", description: "Costi di ricerca, sviluppo e pubblicità", seqNo: 20, summary:false, total:false, debit:true, parent:b1))
		b1.addToSons(new AccountCee(code: "B13", description: "Spese manutenzioni straordinarie da ammortizzare", seqNo: 30, summary:false, total:false, debit:true, parent:b1))
		b1.addToSons(new AccountCee(code: "B14", description: "Oneri pluriennali", seqNo: 40, summary:false, total:false, debit:true, parent:b1))
		b1.addToSons(new AccountCee(code: "B15", description: "Altre immobilizzazioni immateriali", seqNo: 50, summary:false, total:false, debit:true, parent:b1))

		AccountCee b2 = new AccountCee(code: "B2",  description: "Immobilizzazioni materiali", seqNo: 20, summary:true, total:false, debit:true, parent:b)
		b.addToSons(b2)
		b2.addToSons(new AccountCee(code: "B21", description: "Terreni e fabbricati", seqNo: 10, summary:false, total:false, debit:true, parent:b2))
		b2.addToSons(new AccountCee(code: "B22", description: "Impianti ed attrezzature per la donazione", seqNo: 20, summary:false, total:false, debit:true, parent:b2))
		b2.addToSons(new AccountCee(code: "B23", description: "Mobili e macchine d'ufficio", seqNo: 30, summary:false, total:false, debit:true, parent:b2))
		b2.addToSons(new AccountCee(code: "B24", description: "Altri beni", seqNo: 40, summary:false, total:false, debit:true, parent:b2))
		b2.addToSons(new AccountCee(code: "B25", description: "Immobilizzazioni materiali in corso ed acconti", seqNo: 50, summary:false, total:false, debit:true, parent:b2))

		AccountCee b3 = new AccountCee(code: "B3",  description: "Immobilizzazioni finanziarie", seqNo: 30, summary:true, total:false, debit:true, parent:b)
		b.addToSons(b3)
		b3.addToSons(new AccountCee(code: "B31", description: "Partecipazioni", seqNo: 10, summary:false, total:false, debit:true, parent:b3))
		b3.addToSons(new AccountCee(code: "B32", description: "Crediti (che costituiscono immobilizz.) v/altre Avis", seqNo: 20, summary:false, total:false, debit:true, parent:b3))
		b3.addToSons(new AccountCee(code: "B33", description: "Crediti (che costituiscono immobilizz.) v/altri soggetti", seqNo: 30, summary:false, total:false, debit:true, parent:b3))
		b3.addToSons(new AccountCee(code: "B34", description: "Investimenti finanziari pluriennali", seqNo: 40, summary:false, total:false, debit:true, parent:b3))
		b3.addToSons(new AccountCee(code: "B35", description: "Altri titoli (che costituiscono immobilizzazioni)", seqNo: 50, summary:false, total:false, debit:true, parent:b3))
		
		AccountCee c = new AccountCee(code: "C", description: "Attivo circolante", seqNo: 30, summary:true, total:true, debit:true, parent:attivo)
		attivo.addToSons(c)
		AccountCee c1 = new AccountCee(code: "C1", description: "Rimanenze", seqNo: 10, summary:true, total:false, debit:true, parent:c)
		c.addToSons(c1)
		c1.addToSons(new AccountCee(code: "C11", description: "materiale per benemerenze  ", seqNo: 10, summary:false, total:false, debit:true, parent:c1))
		c1.addToSons(new AccountCee(code: "C12", description: "materiale per la propaganda", seqNo: 20, summary:false, total:false, debit:true, parent:c1))
		c1.addToSons(new AccountCee(code: "C13", description: "materiale acquistato per attività di fund raising", seqNo: 30, summary:false, total:false, debit:true, parent:c1))
		c1.addToSons(new AccountCee(code: "C14", description: "materiale donato da terzi per attività di fund raising", seqNo: 40, summary:false, total:false, debit:true, parent:c1))
		c1.addToSons(new AccountCee(code: "C15", description: "altro materiale", seqNo: 50, summary:false, total:false, debit:true, parent:c1))
		c1.addToSons(new AccountCee(code: "C16", description: "lavorazioni in corso ed acconti", seqNo: 60, summary:false, total:false, debit:true, parent:c1))
		
		AccountCee c2 = new AccountCee(code: "C2", description: "Crediti", seqNo: 20, summary:true, total:false, debit:true, parent:c)
		c.addToSons(c2)
		c2.addToSons(new AccountCee(code: "C21", description: "Crediti  per rimborsi su donazioni effettuate", seqNo: 10, summary:false, total:false, debit:true, parent:c2))
		c2.addToSons(new AccountCee(code: "C22", description: "Crediti per liberalità da ricevere", seqNo: 20, summary:false, total:false, debit:true, parent:c2))
		c2.addToSons(new AccountCee(code: "C23", description: "Crediti verso altre Avis", seqNo: 30, summary:false, total:false, debit:true, parent:c2))
		c2.addToSons(new AccountCee(code: "C24", description: "Crediti per contributi da \"cinque per mille\"", seqNo: 40, summary:false, total:false, debit:true, parent:c2))
		c2.addToSons(new AccountCee(code: "C25", description: "Crediti tributari", seqNo: 50, summary:false, total:false, debit:true, parent:c2))
		c2.addToSons(new AccountCee(code: "C26", description: "Crediti verso altri", seqNo: 60, summary:false, total:false, debit:true, parent:c2))
		
		AccountCee c3 = new AccountCee(code: "C3", description: "Attività finanziarie che non costituiscono immobilizzazioni", seqNo: 30, summary:true, total:false, debit:true, parent:c)
		c.addToSons(c3)
		c3.addToSons(new AccountCee(code: "C31", description: "Partecipazioni", seqNo: 10, summary:false, total:false, debit:true, parent:c3))
		c3.addToSons(new AccountCee(code: "C32", description: "Investimenti finanziari", seqNo: 20, summary:false, total:false, debit:true, parent:c3))
		c3.addToSons(new AccountCee(code: "C33", description: "Altri titoli", seqNo: 30, summary:false, total:false, debit:true, parent:c3))
		
		AccountCee c4 = new AccountCee(code: "C4",  description: "Disponibilità liquide", seqNo: 40, summary:true, total:false, debit:true, parent:c)
		c.addToSons(c4)
		c4.addToSons(new AccountCee(code: "C41", description: "Depositi bancari", seqNo: 10, summary:false, total:false, debit:true, parent:c4))
		c4.addToSons(new AccountCee(code: "C42", description: "Conto corrente postale", seqNo: 20, summary:false, total:false, debit:true, parent:c4))
		c4.addToSons(new AccountCee(code: "C43", description: "Assegni e titoli di credito a vista", seqNo: 30, summary:false, total:false, debit:true, parent:c4))
		c4.addToSons(new AccountCee(code: "C44", description: "Denaro contante e valori in cassa", seqNo: 40, summary:false, total:false, debit:true, parent:c4))
		
		AccountCee d = new AccountCee(code: "D", description: "Ratei e risconti attivi", seqNo: 50, summary:true, total:true, debit:true, parent:attivo)
		attivo.addToSons(d)
		d.addToSons(new AccountCee(code: "D11", description: "Ratei attivi", seqNo: 10, summary:false, total:false, debit:true, parent:d))
		d.addToSons(new AccountCee(code: "D12", description: "Risconti attivi", seqNo: 20, summary:false, total:false, debit:true, parent:d))
		
//passivo		
		AccountCee e = new AccountCee(code: "E", description: "Patrimonio netto", seqNo: 10, summary:true, total:true, debit:false, parent:passivo)
		passivo.addToSons(e)
		
		AccountCee e1 = new AccountCee(code: "E1", description: "Patrimonio libero", seqNo: 10, summary:true, total:false, debit:false, parent:e)
		e.addToSons(e1)
		
		e1.addToSons(new AccountCee(code: "E11", description: "Avanzo o disavanzo di gestione dell\'esercizio", seqNo: 10, summary:false, total:false, debit:false, parent:e1))
		e1.addToSons(new AccountCee(code: "E12", description: "Riserve accantonate di esercizi precedenti", seqNo: 20, summary:false, total:false, debit:false, parent:e1))
		e1.addToSons(new AccountCee(code: "E13", description: "Contributi in conto capitalenon vincolati", seqNo: 30, summary:false, total:false, debit:false, parent:e1))
		e1.addToSons(new AccountCee(code: "E14", description: "Rivalutazioni di beni patrimoniali", seqNo: 40, summary:false, total:false, debit:false, parent:e1))
		
		AccountCee e2 = new AccountCee(code: "E2", description: "Fondo di dotazione (se previsto)", seqNo: 20, summary:true, total:false, debit:false, parent:e)
		e.addToSons(e2)
		
		e2.addToSons(new AccountCee(code: "E21", description: "Fondo di dotazione", seqNo: 10, summary:false, total:false, debit:false, parent:e2))
		
		AccountCee e3 = new AccountCee(code: "E3", description: "Patrimonio vincolato", seqNo: 30, summary:true, total:false, debit:false, parent:e)
		e.addToSons(e3)
		
		e3.addToSons(new AccountCee(code: "E31", description: "Fondi vincolati destinati da terzi", seqNo: 10, summary:false, total:false, debit:false, parent:e3))
		e3.addToSons(new AccountCee(code: "E32", description: "Fondi vincolati per decisione di organi istituzionali", seqNo: 20, summary:false, total:false, debit:false, parent:e3))
		e3.addToSons(new AccountCee(code: "E33", description: "Contributi in conto capitale vincolati da  terzi", seqNo: 30, summary:false, total:false, debit:false, parent:e3))
		e3.addToSons(new AccountCee(code: "E34", description: "Riserve statutarie (se previste)", seqNo: 40, summary:false, total:false, debit:false, parent:e3))
		e3.addToSons(new AccountCee(code: "E35", description: "Accantonamenti vincolati per scopi specifici", seqNo: 50, summary:false, total:false, debit:false, parent:e3))
		
		AccountCee f = new AccountCee(code: "F", description: "Fondi per rischi ed oneri", seqNo: 20, summary:true, total:true, debit:false, parent:passivo)
		passivo.addToSons(f)
		f.addToSons(new AccountCee(code: "F11", description: "Fondi per trattamenti di quiescenza e simili", seqNo: 10, summary:false, total:false, debit:false, parent:f))
		f.addToSons(new AccountCee(code: "F12", description: "Altri fondi di accantonamento", seqNo: 20, summary:false, total:false, debit:false, parent:f))
		
		AccountCee g = new AccountCee(code: "G", description: "T.F.R. lavoro subordinato", seqNo: 30, summary:true, total:true, debit:false, parent:passivo)
		passivo.addToSons(g)
		g.addToSons(new AccountCee(code: "G11", description: "Trattamento di fine rapporto di lavoro subordinato", seqNo: 10, summary:false, total:false, debit:false, parent:g))
		
		AccountCee h = new AccountCee(code: "H", description: "Debiti", seqNo: 40, summary:true, total:true, debit:false, parent:passivo)
		passivo.addToSons(h)
		h.addToSons(new AccountCee(code: "H11", description: "Debiti per contributi da erogare", seqNo: 10, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H12", description: "Debiti verso banche", seqNo: 20, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H13", description: "Debiti verso altri finanziatori", seqNo: 30, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H14", description: "Debiti verso fornitori", seqNo: 40, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H15", description: "Debiti tributari", seqNo: 50, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H16", description: "Debiti verso istituti di previdenza", seqNo: 60, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H17", description: "Debiti verso altre Avis", seqNo: 70, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H18", description: "Debiti per rimborsi spese nei confronti di soci volontari", seqNo: 80, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H19", description: "Acconti ricevuti", seqNo: 90, summary:false, total:false, debit:false, parent:h))
		h.addToSons(new AccountCee(code: "H20", description: "Altri debiti", seqNo: 100, summary:false, total:false, debit:false, parent:h))
		
		AccountCee k = new AccountCee(code: "K", description: "Ratei e risconti passivi", seqNo: 50, summary:true, total:true, debit:false, parent:passivo)
		passivo.addToSons(k)
		k.addToSons(new AccountCee(code: "K11", description: "Ratei passivi", seqNo: 10, summary:false, total:false, debit:false, parent:k))
		k.addToSons(new AccountCee(code: "K12", description: "Risconti passivi", seqNo: 20, summary:false, total:false, debit:false, parent:k))
		
		
// Costi
		AccountCee i = new AccountCee(code: "I", description: "Oneri e spese da attività istituzionale", seqNo: 10, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(i)
		AccountCee i11 = new AccountCee(code: "I.11", description: "Acquisti", seqNo: 10, summary:true, total:false, debit:true, parent:i)
		i.addToSons(i11)
		
		i11.addToSons(new AccountCee(code: "I.11.1", description: "Materiale sanitario per raccolta", seqNo: 10, summary:false, total:false, debit:true, parent:i11))
		i11.addToSons(new AccountCee(code: "I.11.2", description: "Materiale per benemerenze", seqNo: 20, summary:false, total:false, debit:true, parent:i11))
		i11.addToSons(new AccountCee(code: "I.11.3", description: "Materiale di consumo", seqNo: 30, summary:false, total:false, debit:true, parent:i11))

		
		AccountCee i12 = new AccountCee(code: "I.12", description: "Servizi", seqNo: 20, summary:true, total:false, debit:true, parent:i)
		i.addToSons(i12)
		
		i12.addToSons(new AccountCee(code: "I.12.1", description: "Aree promozione e propaganda*", seqNo: 10, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.2", description: "Personale e collaboratori per la raccolta", seqNo: 20, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.3", description: "Spese trasporto sangue", seqNo: 30, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.4", description: "Spese per servizio civile", seqNo: 40, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.5", description: "Spese per assemblee e oneri connessi", seqNo: 50, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.6", description: "Quote associative Avis provinciale/regionale/nazionale", seqNo: 60, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.7", description: "Convegni  e formazione volontari e collaboratori", seqNo: 70, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.8", description: "Carburante, assic., manutenz.auto (attività istituz.)", seqNo: 80, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.9", description: "30° anniversario di fondazione*", seqNo: 90, summary:false, total:false, debit:true, parent:i12))
		i12.addToSons(new AccountCee(code: "I.12.10", description: "Altre spese per servizi", seqNo: 100, summary:false, total:false, debit:true, parent:i12))
		
		
		AccountCee i13 = new AccountCee(code: "I.13", description: "Godimento beni di terzi", seqNo: 30, summary:true, total:false, debit:true, parent:i)
		i.addToSons(i13)
		
		i13.addToSons(new AccountCee(code: "I.13.1", description: "Canoni li leasing beni destinati alla raccolta ", seqNo: 10, summary:false, total:false, debit:true, parent:i13))
		i13.addToSons(new AccountCee(code: "I.13.2", description: "Locazione locali destinati alla raccolta", seqNo: 20, summary:false, total:false, debit:true, parent:i13))
		i13.addToSons(new AccountCee(code: "I.14", description: "Ammortamenti immobilizzazioni (attività istituz.)", seqNo: 30, summary:false, total:false, debit:true, parent:i13))
		i13.addToSons(new AccountCee(code: "I.15", description: "Oneri diversi di gestione", seqNo: 40, summary:false, total:false, debit:true, parent:i13))

		AccountCee j = new AccountCee(code: "J", description: "Oneri e spese per attività di raccolta fondi", seqNo: 20, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(j)
		
		j.addToSons(new AccountCee(code: "J.11", description: "Oneri per attività 1", seqNo: 10, summary:false, total:false, debit:true, parent:j))
		j.addToSons(new AccountCee(code: "J.12", description: "Oneri per attività 2", seqNo: 20, summary:false, total:false, debit:true, parent:j))
		j.addToSons(new AccountCee(code: "J.13", description: "Oneri per attività 3", seqNo: 30, summary:false, total:false, debit:true, parent:j))
		j.addToSons(new AccountCee(code: "J.14", description: "Oneri per attività ordinaria di raccolta fondi", seqNo: 40, summary:false, total:false, debit:true, parent:j))

		
		AccountCee m = new AccountCee(code: "M", description: "Oneri e spese per attività accessorie", seqNo: 30, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(m)
		
		m.addToSons(new AccountCee(code: "M.11", description: "Acquisti", seqNo: 10, summary:false, total:false, debit:true, parent:m))
		m.addToSons(new AccountCee(code: "M.12", description: "Servizi", seqNo: 20, summary:false, total:false, debit:true, parent:m))
		m.addToSons(new AccountCee(code: "M.13", description: "Personale dipendente e collaboratori autonomi", seqNo: 30, summary:false, total:false, debit:true, parent:m))
		m.addToSons(new AccountCee(code: "M.14", description: "Godimento beni di terzi", seqNo: 40, summary:false, total:false, debit:true, parent:m))
		m.addToSons(new AccountCee(code: "M.15", description: "Ammortamenti immobilizzaz. attività accessorie", seqNo: 50, summary:false, total:false, debit:true, parent:m))
		m.addToSons(new AccountCee(code: "M.16", description: "Oneri diversi di gestione accessoria", seqNo: 60, summary:false, total:false, debit:true, parent:m))

		
		AccountCee n = new AccountCee(code: "N", description: "Oneri e spese finanziarie e patrimoniali", seqNo: 40, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(n)
		
		AccountCee n11 = new AccountCee(code: "N.11", description: "Oneri da depositi bancari", seqNo: 10, summary:true, total:false, debit:true, parent:n)
		n.addToSons(n11)
		
		
		n11.addToSons(new AccountCee(code: "N.11.1", description: "Interessi passivi", seqNo: 10, summary:false, total:false, debit:true, parent:n11))
		n11.addToSons(new AccountCee(code: "N.11.2", description: "Oneri e spese bancarie", seqNo: 20, summary:false, total:false, debit:true, parent:n11))
		n11.addToSons(new AccountCee(code: "N.11.3", description: "Ritenute fiscali su interessi attivi", seqNo: 30, summary:false, total:false, debit:true, parent:n11))
	
		n.addToSons(new AccountCee(code: "N.12", description: "Interessi ed oneri su altri prestiti ", seqNo: 20, summary:false, total:false, debit:true, parent:n))
		n.addToSons(new AccountCee(code: "N.13", description: "Oneri da patrimonio edilizio", seqNo: 30, summary:false, total:false, debit:true, parent:n))
		n.addToSons(new AccountCee(code: "N.14", description: "Oneri da altri beni patrimoniali", seqNo: 40, summary:false, total:false, debit:true, parent:n))

		
		AccountCee o = new AccountCee(code: "O", description: "Oneri straordinari", seqNo: 50, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(o)
		
		o.addToSons(new AccountCee(code: "O.11", description: "Oneri da attività finanziarie", seqNo: 10, summary:false, total:false, debit:true, parent:o))
		o.addToSons(new AccountCee(code: "O.12", description: "Oneri da attività immobiliari", seqNo: 20, summary:false, total:false, debit:true, parent:o))
		o.addToSons(new AccountCee(code: "O.13", description: "Oneri da altre attività", seqNo: 30, summary:false, total:false, debit:true, parent:o))

		AccountCee p = new AccountCee(code: "P", description: "Oneri e spese di carattere generale ed indivisibile", seqNo: 60, summary:true, total:false, debit:true, parent:costi)
		costi.addToSons(p)
		
		AccountCee p11 = new AccountCee(code: "P.11", description: "Acquisti", seqNo: 10, summary:true, total:false, debit:true, parent:p)
		p.addToSons(p11)
		
		p11.addToSons(new AccountCee(code: "P.11.1", description: "Cancelleria", seqNo: 10, summary:false, total:false, debit:true, parent:p11))
		p11.addToSons(new AccountCee(code: "P.11.2", description: "Riviste e pubblicazioni", seqNo: 20, summary:false, total:false, debit:true, parent:p11))
		
		
		AccountCee p12 = new AccountCee(code: "P.12", description: "Servizi", seqNo: 20, summary:true, total:false, debit:true, parent:p)
		p.addToSons(p12)
		
		p12.addToSons(new AccountCee(code: "P.12.1", description: "Spese postali e telefoniche", seqNo: 10, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.2", description: "Manutenzioni", seqNo: 20, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.3", description: "Omaggi e regalie", seqNo: 30, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.4", description: "Spese pulizia", seqNo: 40, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.5", description: "Assicurazioni volontari", seqNo: 50, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.6", description: "Spese funzionamento organi associativi", seqNo: 60, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.7", description: "Erogazioni liberali", seqNo: 70, summary:false, total:false, debit:true, parent:p12))
		p12.addToSons(new AccountCee(code: "P.12.8", description: "Servizi diversi", seqNo: 80, summary:false, total:false, debit:true, parent:p12))

		
		AccountCee p13 = new AccountCee(code: "P.13", description: "Godimento beni di terzi di supporto generale", seqNo: 30, summary:true, total:false, debit:true, parent:p)
		p.addToSons(p13)
		
		p13.addToSons(new AccountCee(code: "P.13.1", description: "Canoni di leasing", seqNo: 10, summary:false, total:false, debit:true, parent:p13))
		p13.addToSons(new AccountCee(code: "P.13.2", description: "Locazione locali sede amministrativa", seqNo: 20, summary:false, total:false, debit:true, parent:p13))
		p13.addToSons(new AccountCee(code: "P.14", description: "Spese per il personale di supporto generale", seqNo: 30, summary:false, total:false, debit:true, parent:p13))
		p13.addToSons(new AccountCee(code: "P.15", description: "Ammortamenti beni di supporto generale", seqNo: 40, summary:false, total:false, debit:true, parent:p13))
		p13.addToSons(new AccountCee(code: "P.16", description: "Altri oneri di supporto generale", seqNo: 50, summary:false, total:false, debit:true, parent:p13))

// Ricavi

		AccountCee t = new AccountCee(code: "T", description: "Proventi da attività tipiche", seqNo: 10, summary:true, total:false, debit:false, parent:ricavi)
		ricavi.addToSons(t)
		
		AccountCee t11 = new AccountCee(code: "T.11", description: "Convenzioni con Aziende Sanitarie", seqNo: 10, summary:true, total:false, debit:false, parent:t)
		t.addToSons(t11)
		
		t11.addToSons(new AccountCee(code: "T.11.1", description: "Rimborsi per donazioni", seqNo: 10, summary:false, total:false, debit:false, parent:t11))
		t11.addToSons(new AccountCee(code: "T.11.2", description: "Rimborsi per trasporto sangue", seqNo: 20, summary:false, total:false, debit:false, parent:t11))
		
		t.addToSons(new AccountCee(code: "T.12", description: "Contributi su progetti", seqNo: 20, summary:false, total:false, debit:false, parent:t))

		AccountCee t13 = new AccountCee(code: "T.13", description: "Contributi da altri soggetti", seqNo: 20, summary:true, total:false, debit:false, parent:t)
		t.addToSons(t13)
		
		t13.addToSons(new AccountCee(code: "T.13.1", description: "Da privati", seqNo: 40, summary:false, total:false, debit:false, parent:t))
		t13.addToSons(new AccountCee(code: "T.13.2", description: "Da enti pubblici", seqNo: 50, summary:false, total:false, debit:false, parent:t))
		
		t.addToSons(new AccountCee(code: "T.14", description: "Altri proventi", seqNo: 60, summary:false, total:false, debit:false, parent:t))


		AccountCee u = new AccountCee(code: "U", description: "Proventi da attività di raccolta fondi", seqNo: 20, summary:true, total:false, debit:false, parent:ricavi)
		ricavi.addToSons(u)
		u.addToSons(new AccountCee(code: "U.11", description: "Proventi da attività 1", seqNo: 10, summary:false, total:false, debit:false, parent:u))
		u.addToSons(new AccountCee(code: "U.12", description: "Proventi da attività 2", seqNo: 20, summary:false, total:false, debit:false, parent:u))
		u.addToSons(new AccountCee(code: "U.13", description: "Proventi da attività 3", seqNo: 30, summary:false, total:false, debit:false, parent:u))
		u.addToSons(new AccountCee(code: "U.14", description: "Proventi da \"5 per mille\"", seqNo: 40, summary:false, total:false, debit:false, parent:u))
		

		AccountCee v = new AccountCee(code: "V", description: "Proventi da attività accessorie", seqNo: 30, summary:true, total:false, debit:false, parent:ricavi)
		ricavi.addToSons(v)
		v.addToSons(new AccountCee(code: "V.11", description: "Contributi e ricavi per progetti ed iniziative marginali", seqNo: 10, summary:false, total:false, debit:false, parent:v))
		v.addToSons(new AccountCee(code: "V.12", description: "Contributi da soci ed associati", seqNo: 20, summary:false, total:false, debit:false, parent:v))
		v.addToSons(new AccountCee(code: "V.13", description: "Contributi da altri soggetti", seqNo: 30, summary:false, total:false, debit:false, parent:v))
		v.addToSons(new AccountCee(code: "V.14", description: "Altri proventi", seqNo: 40, summary:false, total:false, debit:false, parent:v))

		AccountCee w = new AccountCee(code: "W", description: "Proventi finanziari e patrimoniali", seqNo: 40, summary:true, total:false, debit:false, parent:ricavi)
		ricavi.addToSons(w)
		w.addToSons(new AccountCee(code: "W.11", description: "Proventi lordi da depositi bancari", seqNo: 10, summary:false, total:false, debit:false, parent:w))
		w.addToSons(new AccountCee(code: "W.12", description: "Proventi da investimenti ed altre attività finanziarie", seqNo: 20, summary:false, total:false, debit:false, parent:w))
		w.addToSons(new AccountCee(code: "W.13", description: "Proventi dal patrimonio edilizio", seqNo: 30, summary:false, total:false, debit:false, parent:w))
		w.addToSons(new AccountCee(code: "W.14", description: "Proventi da altri beni patrimoniali", seqNo: 40, summary:false, total:false, debit:false, parent:w))

		AccountCee x = new AccountCee(code: "X", description: "Proventi straordinari", seqNo: 50, summary:true, total:false, debit:false, parent:ricavi)
		ricavi.addToSons(x)
		x.addToSons(new AccountCee(code: "X.11", description: "Proventi da attività finanziarie", seqNo: 10, summary:false, total:false, debit:false, parent:x))
		x.addToSons(new AccountCee(code: "X.12", description: "Proventi da attività immobiliari", seqNo: 20, summary:false, total:false, debit:false, parent:x))
		x.addToSons(new AccountCee(code: "X.13", description: "Proventi da altre attività", seqNo: 30, summary:false, total:false, debit:false, parent:x))
		x.addToSons(new AccountCee(code: "X.14", description: "Ripresa fondo per festa sociale", seqNo: 40, summary:false, total:false, debit:false, parent:x))
	}
	
}
