import org.mesis.bila.*


def d = AccountCee.findByNodeType(NodeType.PATRIMONIALE)
println "node from: " + d


d.rows.each{ row ->
    println("        dare: $row.dare     avere: $row.avere        $row")
    }

//double saldo = d.getSaldo()
//println("doc: $d quadrato: ${d.getBalanced()}")

"Fine"

//println ("account: $acct SEGNO: ${acct.isDebit() ? 'D' : 'A'}")