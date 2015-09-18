import org.mesis.bila.*

def bilaService = ctx.bilaService

println("${Document.count}")
println("${DocumentRow.count}")

bilaService.loadMockDoc(3)

println("${Document.count}")
println("${DocumentRow.count}")


"Fine"