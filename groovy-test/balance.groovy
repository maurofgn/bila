import org.mesis.bila.*

//def bilaService = ctx.bilaService
int year = 2015

//println("${AccountCee.accountsAPCR()}")    //elenco Attivo, Passivo, Costi, Ricavi

def oneRootNode = {NodeType nt ->
    println("---${nt}--------")
    def node = AccountCee.getNodeFromType(nt)
    
    List<BilaRow> bilRows = node.getBalance(year)
    
    bilRows.each { b -> 
        println("${b.nodeType} ${b.level} ${b.accountCee} ${b.summary} ${b.total};${b.creditDebit?.getBalanceYear()}")
    }
}


oneRootNode(NodeType.ATTIVO)
oneRootNode(NodeType.PASSIVO)
oneRootNode(NodeType.COSTI)
oneRootNode(NodeType.RICAVI)

"Fine"