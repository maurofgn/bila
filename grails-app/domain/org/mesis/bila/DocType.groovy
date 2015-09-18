package org.mesis.bila

class DocType {
	static constraints = {
		code(maxSize:20, nullable: false, blank: false, unique: true)
		description(maxSize:60, nullable: false, blank: false)
		active()
	}
	
	String code
	String description
	boolean active = true
//	Date dateCreated
//	Date lastUpdated
 

	String toString(){
		"${description}"
	}
}
