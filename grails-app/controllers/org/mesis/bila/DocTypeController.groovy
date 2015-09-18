package org.mesis.bila



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DocTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DocType.list(params), model:[docTypeInstanceCount: DocType.count()]
    }

    def show(DocType docTypeInstance) {
        respond docTypeInstance
    }

    def create() {
        respond new DocType(params)
    }

    @Transactional
    def save(DocType docTypeInstance) {
        if (docTypeInstance == null) {
            notFound()
            return
        }

        if (docTypeInstance.hasErrors()) {
            respond docTypeInstance.errors, view:'create'
            return
        }

        docTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'docType.label', default: 'DocType'), docTypeInstance.id])
                redirect docTypeInstance
            }
            '*' { respond docTypeInstance, [status: CREATED] }
        }
    }

    def edit(DocType docTypeInstance) {
        respond docTypeInstance
    }

    @Transactional
    def update(DocType docTypeInstance) {
        if (docTypeInstance == null) {
            notFound()
            return
        }

        if (docTypeInstance.hasErrors()) {
            respond docTypeInstance.errors, view:'edit'
            return
        }

        docTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DocType.label', default: 'DocType'), docTypeInstance.id])
                redirect docTypeInstance
            }
            '*'{ respond docTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DocType docTypeInstance) {

        if (docTypeInstance == null) {
            notFound()
            return
        }

        docTypeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DocType.label', default: 'DocType'), docTypeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'docType.label', default: 'DocType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
