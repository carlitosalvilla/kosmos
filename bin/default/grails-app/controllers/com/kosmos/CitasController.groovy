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
       //evaluamos si ya hay un consutorio con ese horario y con ese medico
       def lista=  citasService.list()
         groovy.inspect.swingui.ObjectBrowser.inspect(lista)
         
        for(def i=0;i<=lista.size(); i++){
            
            if(lista[i].consultorio==citas.consultorio &&lista[i].fecha==citas.fecha &&lista[i].horario==citas.horario ){
              request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'citas.label', default: 'Citas', text: 'ya hay una cita para este consultorio'), citas.id])
               
            }
            '*' { respond citas, [status: FAILED] }
        }  
            }
            
        }
        //ahora el doctor a esa hora
        
      for(def i=0;i<=lista.size(); i++){
            
            if(lista[i].medico==citas.medico &&lista[i].fecha==citas.fecha &&lista[i].horario==citas.horario ){
              request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'citas.label', default: 'Citas', text: 'ya hay una cita para este medico'), citas.id])
               
            }
            '*' { respond citas, [status: FAILED] }
        }  
            }
            
        }
        //ahora el paciente
          for(def i=0;i<=lista.size(); i++){
            
            if(lista[i].paciente==citas.paciente &&lista[i].fecha==citas.fecha &&(hour(citas.horario)>3) ){
              request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'citas.label', default: 'Citas', text: 'ya hay una cita para este medico'), citas.id])
               
            }
            '*' { respond citas, [status: FAILED] }
        }  
            }
            
        }
        
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
