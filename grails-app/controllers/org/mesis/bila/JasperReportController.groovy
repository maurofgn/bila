package org.mesis.bila

import org.mesis.bila.*

class JasperReportController {
	
    /* shows the records listing page*/
    def index() {
		render(view:"index")
    }
	
    /* serves the report document for download*/
    def generateReport() {
		
		if (!params.year) {
			params.year = new Date()[Calendar.YEAR]
		}
		
		Integer year = Integer.parseInt(params.year)	// ? Integer.parseInt(params.year) : new Date()[Calendar.YEAR]
		params.year = year
		params.yearPrev = year-1

//parametri del report
        params.title = "Bilancio"
        params.reportId = "Mesis srl via dei Velini 19 Macerata Italy"
        params.operationTime = new Date()
		//il nome del file di output, il formato (pdf, xls, html) e il nome del file .jrxml viene passato dalla gsp con il tag 
		
		def oneRootNode = {NodeType nt ->
			def node = AccountCee.getNodeFromType(nt)
			return node?.getBalance(year)
		}
		List<BilaRow> bilaRows = []
		
		bilaRows += oneRootNode(NodeType.ATTIVO)
		bilaRows += oneRootNode(NodeType.PASSIVO)
		bilaRows += oneRootNode(NodeType.COSTI)
		bilaRows += oneRootNode(NodeType.RICAVI)
		
		
//		Map result = [:]
//        result.data = []
//        result.data = << [code:"vivek",description:"yadav"] // from here you can send any type of data  
//		
//		
////        result.data = bilarows	//<< [name:”vivek”,surName:”yadav”] // from here you can send any type of data  
//  // what ever you want
//        JasperReportDef rep = jasperService.buildReportDefinition(params, request.locale, result)
//        ByteArrayOutputStream stream = jasperService.generateReport(rep)
//        response.setHeader("Content-disposition", "attachment; filename=" + 'fileName' + ".pdf")
//        response.contentType = "application/pdf"
//        response.outputStream << stream.toByteArray()
		
		//chiamata al controller jasper
        chain(controller:'jasper', action:'index',
            model:[data:bilaRows],
            params:params)
			
//		render(view:"index")
        return false
    }
}