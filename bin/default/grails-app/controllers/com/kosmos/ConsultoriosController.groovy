package com.kosmos

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ConsultoriosController {

    ConsultoriosService consultoriosService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond consultoriosService.list(params), model:[consultoriosCount: consultoriosService.count()]
    }

    def show(Long id) {
        respond consultoriosService.get(id)
    }

    def create() {
        respond new Consultorios(params)
    }

    def save(Consultorios consultorios) {
        if (consultorios == null) {
            notFound()
            return
        }

        try {
            consultoriosService.save(consultorios)
        } catch (ValidationException e) {
            respond consultorios.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consultorios.label', default: 'Consultorios'), consultorios.id])
                redirect consultorios
            }
            '*' { respond consultorios, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond consultoriosService.get(id)
    }

    def update(Consultorios consultorios) {
        if (consultorios == null) {
            notFound()
            return
        }

        try {
            consultoriosService.save(consultorios)
        } catch (ValidationException e) {
            respond consultorios.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consultorios.label', default: 'Consultorios'), consultorios.id])
                redirect consultorios
            }
            '*'{ respond consultorios, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        consultoriosService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consultorios.label', default: 'Consultorios'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consultorios.label', default: 'Consultorios'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
