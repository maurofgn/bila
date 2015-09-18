package org.mesis.bila

class BilaRow {
	NodeType nodeType
	int level
	String code
	String description
	boolean summary
	boolean total
	
	double amountYear = 0
	double amountYearPre = 0
	double amountDelta = 0
	
	AccountCee accountCee
	CreditDebit creditDebit
	
	String toString(){
		"${accountCee} ${creditDebit}"
	}
	
}
