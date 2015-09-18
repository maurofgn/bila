package org.mesis.bila

class Avis {

	String name
	String address
	String city
	Integer postalCode
	String region
	String email
	String phone
	String contactName
	boolean active = true
	Date dateCreated
	Date lastUpdated

	static constraints = {
		name(maxSize:60, nullable: false, blank: false)
		address(maxSize:60, nullable: false, blank: false)
		city(maxSize:60, nullable: false, blank: false)
		postalCode(min: 0, max:99999, nullable: false)
		region(maxSize:4, nullable: false, blank: false)
		email(maxSize:60, email: true, blank: true)
		phone(maxSize:20, nullable: true)
		contactName(maxSize:60, nullable: true)
		active(display:false)
	}

	static mapping = {
			autoTimestamp true
	}	
	
	String toString(){
		"${name}"
	}
}

