import org.mesis.bila.*

def bilaService = ctx.bilaService

println ("Nr Rec iniziale di AccountCee: $AccountCee.count")


List<AccountCee> allNode = bilaService.getNodesList()

allNode.each { node ->
    if (!node.summary && !node.nodeType) {
         //println  node.account
         node.account.save(failOnError: true)
//         c.save(failOnError: true)
        }
       
    }



"Fine"