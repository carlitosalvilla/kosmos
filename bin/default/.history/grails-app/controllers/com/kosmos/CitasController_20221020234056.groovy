package com.kosmos

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CitasController {

    CitasService citasService
    DoctorService doctorService
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond citasService.list(params), model:[citasCount: citasService.count()]
    }

    def show(Long id) {
        respond citasService.get(id)
    }

    def create() {
        respond new Citas(params)
    }

    def save(Citas citas) {
        
         //println(new JSON(delegate).toString())
         
       // params.max = Math.min(max ?: 1000, 100000)
       def lista=  citasService.list()
         lista.dump();
         System.exit(0)
         
        
        
        if (citas == null) {
            notFound()
            return
        }

        try {
            citasService.save(citas)
        } catch (ValidationException e) {
            respond citas.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'citas.label', default: 'Citas'), citas.id])
                redirect citas
            }
            '*' { respond citas, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond citasService.get(id)
    }

    def update(Citas citas) {
        if (citas == null) {
            notFound()
            return
        }

        try {
            citasService.save(citas)
        } catch (ValidationException e) {
            respond citas.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'citas.label', default: 'Citas'), citas.id])
                redirect citas
            }
            '*'{ respond citas, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        citasService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'citas.label', default: 'Citas'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'citas.label', default: 'Citas'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
