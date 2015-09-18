import org.mesis.bila.*

def bilaService = ctx.bilaService

println("${Document.count}")
println("${DocumentRow.count}")

bilaService.loadMockDoc()

println("${Document.count}")
println("${DocumentRow.count}")


"Fine"