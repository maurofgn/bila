import org.mesis.bila.*

//def bilaService = ctx.bilaService


//println ("Nr Rec iniziale di AccountCee: $AccountCee.count")
//println ("Nr Rec iniziale di DocType: $DocType.count")

    def loadDocType() {
        if (DocType.count)
            return;    //già caricato
            
        new DocType(code: "acqFat", description: "Fattura di Acquisto").save()
        new DocType(code: "acqRic", description: "Ricevuta di Acquisto").save()
        
        new DocType(code: "venFat", description: "Fattura di Vendita").save()
        new DocType(code: "venRic", description: "Ricevuta di Vendita").save()
        
        new DocType(code: "cassaUscita", description: "Uscita di cassa").save()
        new DocType(code: "cassaEntrata", description: "Entrata di cassa").save()
        
        new DocType(code: "bancaAddebito", description: "Addebito bancario").save()
        new DocType(code: "bancaAccredito", description: "Accredito bancario").save()
        
        new DocType(code: "giro", description: "Giroconti").save()
    }


//println ("Nr Rec iniziale di DocType: $DocType.count")
//loadDocType()
println ("Records di DocType:")  

"Fine"