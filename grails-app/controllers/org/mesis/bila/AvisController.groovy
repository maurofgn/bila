package org.mesis.bila



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AvisController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond Avis.list(params), model:[avisInstanceCount: Avis.count()]

		Avis avisInstance = Avis.first()
		
		if (avisInstance)
			redirect action: 'show', id:avisInstance.id	//show(avisInstance)
		else
			redirect action: 'create'

//        respond avisInstance, model:[avisInstanceCount: avisInstance ? 1 : 0]
		
//		Avis.first()
//		redirect(action: "show")
    }

    def show(Avis avisInstance) {
        respond avisInstance
    }

    def create() {
        respond new Avis(params)
    }

    @Transactional
    def save(Avis avisInstance) {
        if (avisInstance == null) {
            notFound()
            return
        }

        if (avisInstance.hasErrors()) {
            respond avisInstance.errors, view:'create'
            return
        }

        avisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'avis.label', default: 'Avis'), avisInstance.id])
                redirect avisInstance
            }
            '*' { respond avisInstance, [status: CREATED] }
        }
    }

    def edit(Avis avisInstance) {
        respond avisInstance
    }

    @Transactional
    def update(Avis avisInstance) {
        if (avisInstance == null) {
            notFound()
            return
        }

        if (avisInstance.hasErrors()) {
            respond avisInstance.errors, view:'edit'
            return
        }

        avisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Avis.label', default: 'Avis'), avisInstance.id])
                redirect avisInstance
            }
            '*'{ respond avisInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Avis avisInstance) {

        if (avisInstance == null) {
            notFound()
            return
        }

        avisInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Avis.label', default: 'Avis'), avisInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'avis.label', default: 'Avis'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
