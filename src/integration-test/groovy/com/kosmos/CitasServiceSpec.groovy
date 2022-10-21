package com.kosmos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CitasServiceSpec extends Specification {

    CitasService citasService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Citas(...).save(flush: true, failOnError: true)
        //new Citas(...).save(flush: true, failOnError: true)
        //Citas citas = new Citas(...).save(flush: true, failOnError: true)
        //new Citas(...).save(flush: true, failOnError: true)
        //new Citas(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //citas.id
    }

    void "test get"() {
        setupData()

        expect:
        citasService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Citas> citasList = citasService.list(max: 2, offset: 2)

        then:
        citasList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        citasService.count() == 5
    }

    void "test delete"() {
        Long citasId = setupData()

        expect:
        citasService.count() == 5

        when:
        citasService.delete(citasId)
        sessionFactory.currentSession.flush()

        then:
        citasService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Citas citas = new Citas()
        citasService.save(citas)

        then:
        citas.id != null
    }
}
