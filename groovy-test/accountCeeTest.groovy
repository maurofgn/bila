import org.mesis.bila.*


def bilaService = ctx.bilaService
int year = 2015

if (!AccountCee.count()) {
        bilaService.loadBilaCee()
        bilaService.loadPC()
        bilaService.loadDocType()
        }
bilaService.loadMockDoc()

//println("${AccountCee.accountsAPCR()}")    //elenco Attivo, Passivo, Costi, Ricavi

def attivo = AccountCee.getNodeFromType(NodeType.ATTIVO)
println("${attivo}")    //Attivo

//List<BilaRow> attiviBal = attivo.getBalance(year)
//attiviBal.each { b -> 
//    println("${b.code} ${b.accountCee} [saldo: ${b.creditDebit.getBalanceYear()}] ${b.amountDelta}")
//}
//return "OK"

println("--------------")
List<AccountCee> attivi = attivo.allSons
attivi.each { c -> 
    CreditDebit crDe = c.creditDebit(year)
    println("${c} [${c.total}] saldo: ${crDe.getBalanceYear()}")

    c.accounts?.each { pc ->
        println("\tpiano dei conti: ${pc} ${pc.creditDebit(year)}")
    }

}
println("--------------")

//println("${AccountCee.getNodeFromType(NodeType.PASSIVO)}") 
//println("${AccountCee.getNodeFromType(NodeType.COSTI)}") 
//println("${AccountCee.getNodeFromType(NodeType.RICAVI)}") 


//println("${accountCee.rootNode}")

//AccountCee accountCee.getRootNode()

//def acctCriteria = Account.createCriteria()
//def accounts =  Account.createCriteria().list { le("code", "C31") }

//accounts.each{ account ->

//    println("conto $account (${account.getNaturalSign()}) ${account.creditDebit(2015)}")
//    
//    CreditDebit cd2 = account.creditDebit(2015)
//    println("saldo conto: ${cd2.balanceYear}")

//    CreditDebit cd = bilaService.creditDebit(2015, account)
//    println("saldo conto: ${cd.balanceYear}")

//}

"Fine"