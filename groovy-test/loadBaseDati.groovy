import org.mesis.bila.*

def bilaService = ctx.bilaService

println ("Nr Rec iniziale di AccountCee: $AccountCee.count")

if (!AccountCee.count()) {
        bilaService.loadBilaCee()
        bilaService.loadPC()
        bilaService.loadDocType()
        bilaService.loadMockDoc() 
        }

println "Records di account = ${Account.count}"

"Fine"