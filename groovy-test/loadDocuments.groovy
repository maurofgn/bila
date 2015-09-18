import org.mesis.bila.*

def bilaService = ctx.bilaService

if (!AccountCee.count()) {
	bilaService.loadBilaCee()
	bilaService.loadPC()
	bilaService.loadDocType()
}

println("${Document.count}")
println("${DocumentRow.count}")

//Document.lis().each{ doc ->     doc.delete()}

bilaService.loadMockDoc(10)

println("${Document.count}")
println("${DocumentRow.count}")


"Fine"
