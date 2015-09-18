import org.mesis.bila.*

def bilaService = ctx.bilaService

//def acctCriteria = Account.createCriteria()
def accounts =  Account.createCriteria().list { le("code", "C31") }

accounts.each{ account ->

    println("conto $account (${account.getNaturalSign()}) ${account.creditDebit(2015)}")
//    
//    CreditDebit cd2 = account.creditDebit(2015)
//    println("saldo conto: ${cd2.balanceYear}")

//    CreditDebit cd = bilaService.creditDebit(2015, account)
//    println("saldo conto: ${cd.balanceYear}")

}

"Fine"