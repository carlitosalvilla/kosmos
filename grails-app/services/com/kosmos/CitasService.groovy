package com.kosmos

import grails.gorm.services.Service

@Service(Citas)
interface CitasService {

    Citas get(Serializable id)

    List<Citas> list(Map args)

    Long count()

    void delete(Serializable id)

    Citas save(Citas citas)

}