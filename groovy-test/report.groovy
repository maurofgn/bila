import org.mesis.bila.*

int year = new Date()[Calendar.YEAR]

//println ("anno: $year")

def oneRootNode = {NodeType nt ->
            def node = AccountCee.getNodeFromType(nt)
            return node?.getBalance(year)
        }
        
List<BilaRow> bilaRows = []
        
bilaRows += oneRootNode(NodeType.ATTIVO)
//bilaRows += oneRootNode(NodeType.PASSIVO)
//bilaRows += oneRootNode(NodeType.COSTI)
//bilaRows += oneRootNode(NodeType.RICAVI)

//bilaRows.each{ r ->
//    println("${r.toString()}")
//}

println("${bilaRows.size}")

def params = [year:2015, jrxmlName:"bila.jrxml"]

chain(controller:'jasper', action:'index', model:[data:bilaRows], params:params)

"OK"
