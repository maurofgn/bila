package org.mesis.bila

class Account {

	String code
	String description
	boolean active = true
	AccountCee accountCee
//	Date dateCreated
//	Date lastUpdated

	static constraints = {
		code(maxSize:10, nullable: false, blank: false, unique: true)
		description(maxSize:60, nullable: false, blank: false)
		active()
		accountCee(nullable: true)
	}
	
	static hasMany = [documentRows: DocumentRow]

	/**
	* @return il segno naturale del conto 
	*/
	boolean isDebit() {
		return accountCee?.getAncestorDebit()
	}
	
	String getNaturalSign() {
		return isDebit() ? "D" : "A"
	}
	
	
	/**
	* restituisce il totale dare e avere per anno e anno-1 del conto
	*/
	CreditDebit creditDebit(int year) {
		CreditDebit retValue = new CreditDebit(year: year)
		documentRows?.each { r ->
			retValue.addRow(r)
        }
		return retValue
	}

	String toString(){
		"${code} - ${description}"
	}
}
