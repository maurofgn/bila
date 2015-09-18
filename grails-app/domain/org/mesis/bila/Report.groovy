package org.mesis.bila

class Report {

	String code
	String description
	String modelName
	String outputFileName
	String formatType = 'PDF'
	boolean active = true
	Date dateCreated
	Date lastUpdated

	static constraints = {
		code(maxSize:20, nullable: false, blank: false, unique: true)
		description(maxSize:60, nullable: false, blank: false)
		modelName(maxSize:60, nullable: false, blank: false)
		outputFileName(maxSize:60, nullable: false, blank: false)
		formatType(maxSize:4, nullable: false, blank: false, inList: ["PDF", "HTML", "XML","CSV", "XLS", "RTF","TEXT", "ODT", "ODS","DOCX", "XLSX", "PPTX"])
		active()
	}
	
	static mapping = {
			autoTimestamp true
	}	
	
	String toString(){
		"${description}"
	}
}
