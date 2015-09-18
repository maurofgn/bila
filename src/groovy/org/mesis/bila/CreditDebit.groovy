package org.mesis.bila

import org.mesis.bila.*

	class CreditDebit {
		Integer year			//anno di riferimento
		double dareYear			//dare dell'anno
		double avereYear		//avere dell'anno
		double dareYearPrev		//dare dell'anno precedente
		double avereYearPrev	//avere dell'anno precedente
		String naturalSign		//segno del conto. In base all'appartenenza a attivo (D), passivo (A), costo (D), ricavo (A)
		
		def addRow(DocumentRow row) {
			int yy = row?.document?.dateReg[Calendar.YEAR]
			if (yy == year) {
				dareYear += row.dare
				avereYear += row.avere
			} else if (yy == year-1) {
				dareYearPrev += row.dare
				avereYearPrev += row.avere
			}
		}
		
		def add(CreditDebit cd) {
			
			if (cd && cd.year == year) {
				dareYear += cd.dareYear
				avereYear += cd.avereYear
				dareYearPrev += cd.dareYearPrev
				avereYearPrev += cd.avereYearPrev
			}
		}
		
		double getBalanceYear() {
			return dareYear - avereYear
		}
		
		double getBalanceYearPrev() {
			return dareYearPrev - avereYearPrev
		}
		
		boolean isEmpty() {
			return dareYear == 0 && avereYear == 0 && dareYearPrev == 0 && avereYearPrev == 0
		}
		
		String toString(){
			"${year} saldo: ${getBalanceYear()}"
		}
	}
