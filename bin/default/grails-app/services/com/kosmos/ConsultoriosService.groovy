package com.kosmos

import grails.gorm.services.Service

@Service(Consultorios)
interface ConsultoriosService {

    Consultorios get(Serializable id)

    List<Consultorios> list(Map args)

    Long count()

    void delete(Serializable id)

    Consultorios save(Consultorios consultorios)

}