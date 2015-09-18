import org.mesis.bila.*


def getParentsTotal(AccountCee maxParent = null, AccountCee c) {

    def allParent = []    //new ArrayList()
    
    if (c.isLeaf() && c.isLastBrother()) {
        
        def climb
        
        climb = { node, level = -1 ->
        
            if (node) {
                if (node.total) {
                    node.level = level
                    allParent.add(node)
                }
//                println("total(${node.total}) maxParent(${maxParent?.id ?:0} != ${node?.id ?:0}) lastBrother(${node.isLastBrother()}) maxParNodetf( ${maxParent?.id ?:0} != ${node?.id ?:0} ) node in climb ${node}")
                if (node.isLastBrother() && ((maxParent?.id?:0) != (node?.id?:0))) {
                
                    if ( (maxParent?.id ?:0) == (node?.id?:0))
                        println("node ------ (${node}) ")
                    
                
                
                    climb(node.parent, (level-1))
                    }
            }
        }
        
        climb(c.parent)
    }
    return allParent
}


def xx

xx = {a ->
    if (a) {
        println("${a.code} ultimo fratello: ${a.isLastBrother() ? 'Y' : 'N'}    ${a.total ? 'totale' : 'NON totale'}     figlio di:${a.parent?.code} ${a.parent?.description}")
        xx(a.parent)
    }
}

def costi = AccountCee.getNodeFromType(NodeType.COSTI)
AccountCee c = AccountCee.findByCode("P.16")

println("${c.code} foglia: ${c.isLeaf() ? 'Y' : 'N'} ultimo fratello: ${c.isLastBrother() ? 'Y' : 'N'}")
//xx(c)

println("-----------getParentsTotal----------- ")
 
List<AccountCee> parents = c.getParentsTotal(costi)

//List<AccountCee> parents = getParentsTotal(costi, c)


parents.each { b -> 
    println("${b.nodeType} ${b.level} ${b.summary} id:${b.id} ${b} ")
}

"Fine"