//import gorm.recipes.*
import org.mesis.bila.*

new BootStrap().init()

def allNode
def traverse

traverse = { node, level=0 ->

    if (node) {
        if (level == 0)
            allNode = new ArrayList()
       // println "${'|  ' * arg}- ${node.getDescription()}\\"
        node.level = level
        allNode.add(node)
        
        if (node.sons)
            node.sons.each {oneSon -> traverse(oneSon, (level+1)) }
        
    }
}

def nodeRoot = AccountCee.findByNodeType(NodeType.ROOT)
//def nodeRoot = AccountCee.findByNodeType(NodeType.RICAVI)

traverse(nodeRoot)

println "----------all node from ${nodeRoot.code}"

allNode.each { node ->
//    println ("${'|  ' * node.level} ${node.toString()} summary=${node.summary} ${node.parent?'PARENT=' + node.parent.code:''}")
    println ("${'|  ' * node.level}- ${node.toString()} ${node.total?' CON TOTALE':''}")
}

retValue = '--Fine'
