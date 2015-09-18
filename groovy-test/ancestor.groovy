import org.mesis.bila.*

Account acct = Account.findByCode("P.12.1")
println ("account: $acct SEGNO: ${acct.isDebit() ? 'D' : 'A'}")


StringBuffer sb = new StringBuffer()
sb.append("SELECT "); 
//sb.append("sum(r.amount) "); 
sb.append("sum(case when r.debit then r.amount else 0 end) "); 
//sb.append("sum(case when r.debit then 0 else r.amount end)"); 
//sb.append("count(*) "); 
sb.append("FROM DocumentRow r "); 
//sb.append("inner join Account pc on pc.id = r.account_id "); 
//sb.append("inner join Document d on d.id = r.document_id "); 
sb.append("where "); 
sb.append("year(r.document.dateReg) = 2015 "); 
sb.append("and r.account.id = 1 ");

def codeAndDebit = DocumentRow.executeQuery(sb.toString())
double amt = row[0]
println ("$amt")
  
//for (row in codeAndDebit) {
//   double dare = row[0]
//   double avere = row[1]
//   boolean active = row[1]
//   println ("$code $active")
//   println ("$row $amt")
//}


"Fine"