import org.mesis.bila.*

def bilaService = ctx.bilaService
int year = 2015

def par = AccountCee.getNodeFromType(NodeType.ROOT)

List<AccountCee> allSons = par.getAllSons()

return allSons?.size

allSons.each { c -> 
    println("${c.isLastBrother() ? 'ultimo fratello' : 'altro'} ${c}     PARENT: ${c.parent} ${c.parent?.total}")
}

return "OK"

//println("${AccountCee.accountsAPCR()}")    //elenco Attivo, Passivo, Costi, Ricavi

def oneRootNode = {NodeType nt ->
    println("---${nt}--------")
    def node = AccountCee.getNodeFromType(nt)
    
    List<BilaRow> bilRows = node.getBalance(year)
    
    bilRows.each { b -> 
        println("${b.accountCee} ${b.summary} ${b.total};${b.creditDebit.getBalanceYear()}")
    }
}


oneRootNode(NodeType.ATTIVO)
oneRootNode(NodeType.PASSIVO)
oneRootNode(NodeType.COSTI)
oneRootNode(NodeType.RICAVI)


//def attivo = AccountCee.getNodeFromType(NodeType.ATTIVO)
//List<BilaRow> attiviBal = attivo.getBalance(year)
//attiviBal.each { b -> 
//    println("${b.accountCee};${b.creditDebit.getBalanceYear()}")
//}

return "OK"

println("--------------")
List<AccountCee> attivi = attivo.allSons
attivi.each { c -> 
    CreditDebit crDe = c.creditDebit(year)
    println("${c} [${c.total}] saldo: ${crDe.getBalanceYear()}")

    c.accounts.each { pc ->
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