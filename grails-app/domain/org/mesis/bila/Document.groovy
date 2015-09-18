package org.mesis.bila

class Document implements Comparable<Document>{

	DocType docType
	Date dateReg
	Date dateDoc
	String nrDoc
	String note
	Double amount = 0
	
	SortedSet rows
	
//	Date dateCreated
//	Date lastUpdated

	boolean valid = false
	Double dare = 0
	Double avere = 0

	static constraints = {
		docType(nullable: false)
		dateReg(nullable: false)
		dateDoc(nullable: false)
		nrDoc(maxSize:60, nullable: false, blank: false)
		amount(nullable: false, min: 0.01d, scale: 2)
		valid(nullable: false, display: false)
//		dare(nullable: false, display: false)
//		avere(nullable: false, display: false)
		note(nullable: true)
	}

	static hasMany = [rows: DocumentRow]
	
	static mapping = {
//		dare  formula: "(Select sum(b.dare)  FROM DocumentRow b )"
//		avere formula: "(Select sum(b.avere) FROM DocumentRow b WHERE b.document_id = id)"
	}
	
	static transients = ['valid', 'dare', 'avere']
	
//	Double getDare() {
//		setDareAvere()
//		return dare
//	}
//
//	Double getAvere() {
//		setDareAvere()
//		return avere
//	}
	
	Double getSaldo() {
//		setDareAvere()
		return dare - avere
	}
	
	boolean isValid() {
		return dare == avere
	}

	Double getTotal() {
//		setDareAvere()
		return Math.max(dare, avere) 
	}
	
	def onLoad = {
		dare = 0
		avere = 0
		rows?.each{ row -> 
			dare = dare + row.dare
			avere = avere + row.avere
			}
    }
	
//	private void setDareAvere() {
//		Double d = 0
//		Double a = 0
//		rows?.each{ row -> 
//			d = d + row.getDare()
//			a = a + row.getAvere()
//			}
//		dare = d
//		avere = a
//	}
	
	@Override
	public int compareTo(Document o) {
		int c = dateReg <=> o.dateReg
		if (c != 0)
			return c
			
		c = docType.id <=> o.docType.id
		if (c != 0)
			return c
			
		c = dateDoc <=> o.dateDoc
		if (c != 0)
			return c
			
		c = nrDoc <=> o.nrDoc
		if (c != 0)
			return c

		return id <=> o.id	
	}

	String toString(){
		String dateDocFormatted = dateDoc?.format( 'd-M-yyyy' )
		"${docType} ${dateDocFormatted} Nr. ${nrDoc}"
	}

}
