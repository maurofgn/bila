package org.mesis.bila



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DocumentRowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DocumentRow.list(params), model:[documentRowInstanceCount: DocumentRow.count()]
    }

    def show(DocumentRow documentRowInstance) {
        respond documentRowInstance
    }

    def create() {
        respond new DocumentRow(params)
    }

    @Transactional
    def save(DocumentRow documentRowInstance) {
        if (documentRowInstance == null) {
            notFound()
            return
        }

        if (documentRowInstance.hasErrors()) {
            respond documentRowInstance.errors, view:'create'
            return
        }

        documentRowInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'documentRow.label', default: 'DocumentRow'), documentRowInstance.id])
                redirect documentRowInstance
            }
            '*' { respond documentRowInstance, [status: CREATED] }
        }
    }

    def edit(DocumentRow documentRowInstance) {
        respond documentRowInstance
    }

    @Transactional
    def update(DocumentRow documentRowInstance) {
        if (documentRowInstance == null) {
            notFound()
            return
        }

        if (documentRowInstance.hasErrors()) {
            respond documentRowInstance.errors, view:'edit'
            return
        }

        documentRowInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DocumentRow.label', default: 'DocumentRow'), documentRowInstance.id])
                redirect documentRowInstance
            }
            '*'{ respond documentRowInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DocumentRow documentRowInstance) {

        if (documentRowInstance == null) {
            notFound()
            return
        }

        documentRowInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DocumentRow.label', default: 'DocumentRow'), documentRowInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentRow.label', default: 'DocumentRow'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
