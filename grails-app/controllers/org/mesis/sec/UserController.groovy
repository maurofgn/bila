package org.mesis.sec



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def beforeInterceptor = [action:this.&auth, except:["index", "show", "login", "authenticate", "logout"]]

	def auth() {
		if(!session.user) {
		  redirect(controller:"user", action:"login")
		  return false
		}
		
		if( !(session?.user?.role == "Admin") ){
		  flash.message = "Devi essere un amministratore per eseguire questa attività."
		  redirect(controller:"user", action:"index")
		  return false
		}
	}
	
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }
	
	def login = {}

	def authenticate = {
		def user = User.findByLoginAndPassword(params.login, params.password)
		if(user){
			  session.user = user
			  flash.message = "Ben tornato ${user.name}!"
//			  redirect(controller:"user", action:"index")
//			  redirect(uri: "/")
			  redirect(controller:"user", action:"index")    
			}else{
			  flash.message = "Account non valido, ${params.login}. Prova ancora."
			  redirect(action:"login")
			}
	}

	def logout = {
		flash.message = "Goodbye ${session.user.name}"
		session.user = null
//		redirect(controller:"entry", action:"list")
		 redirect(uri: "/")
	}	
	

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
