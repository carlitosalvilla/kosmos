package com.kosmos

class Citas {

    String consutorio;
    String medico;
    String  fecha;
    String horario;
    String paciente

    static dr = [medicos: Doctor]

    static mapping = {
        medicos joinTable: [name: "Doctor", key: 'idDoctor' ]
    }

    static constraints = {
         
    }
}
