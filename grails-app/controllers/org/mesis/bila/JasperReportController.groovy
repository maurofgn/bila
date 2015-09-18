package org.mesis.bila

import org.mesis.bila.*

class JasperReportController {
	
    /* shows the records listing page*/
    def index() {
//        def jasperDTOList = bilaService.loadAllJasperDTO(20)
//        render(view:"index", model:[jasperDTOList:jasperDTOList])
		render(view:"index")
//		redirect action: 'generateReport'
    }
	
    /* serves the report document for download*/
    def generateReport() {
		
		int year = params.year
		
		if (!year) {
			year = new Date()[Calendar.YEAR]
			params.year = year
		}
	
//parametri identificativi del template


//_format=PDF&_name=all_dto&_file=all_dto

//		params.format="PDF"
////		params._name="bilancio.jrxml"
//		params.name="bilancio_${year}"
////		params._file="bilancio_${year}.PDF"
//		params.file="bilancio"
////////////
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
		
		//chiamata al controller jasper
        chain(controller:'jasper', action:'index',
            model:[data:bilaRows],
            params:params)
        return false
    }
}