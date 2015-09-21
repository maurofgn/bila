package org.mesis.sec

class User {
	
  static constraints = {
    login(unique:true)
    password(password:true)
    name()
    role(inList:["user", "admin"])
  }
  
//  static hasMany = [entries:Entry]
  
  String login
  String password
  String name
  String role
  
  String toString(){
    name
  }
  
}
