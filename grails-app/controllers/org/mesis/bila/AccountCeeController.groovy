package org.mesis.bila



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccountCeeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AccountCee.list(params), model:[accountCeeInstanceCount: AccountCee.count()]
    }

    def show(AccountCee accountCeeInstance) {
        respond accountCeeInstance
    }

    def create() {
        respond new AccountCee(params)
    }

    @Transactional
    def save(AccountCee accountCeeInstance) {
        if (accountCeeInstance == null) {
            notFound()
            return
        }

        if (accountCeeInstance.hasErrors()) {
            respond accountCeeInstance.errors, view:'create'
            return
        }

        accountCeeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accountCee.label', default: 'AccountCee'), accountCeeInstance.id])
                redirect accountCeeInstance
            }
            '*' { respond accountCeeInstance, [status: CREATED] }
        }
    }

    def edit(AccountCee accountCeeInstance) {
        respond accountCeeInstance
    }

    @Transactional
    def update(AccountCee accountCeeInstance) {
        if (accountCeeInstance == null) {
            notFound()
            return
        }

        if (accountCeeInstance.hasErrors()) {
            respond accountCeeInstance.errors, view:'edit'
            return
        }

        accountCeeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AccountCee.label', default: 'AccountCee'), accountCeeInstance.id])
                redirect accountCeeInstance
            }
            '*'{ respond accountCeeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AccountCee accountCeeInstance) {

        if (accountCeeInstance == null) {
            notFound()
            return
        }

        accountCeeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AccountCee.label', default: 'AccountCee'), accountCeeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountCee.label', default: 'AccountCee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
