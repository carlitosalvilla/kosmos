package com.kosmos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConsultoriosServiceSpec extends Specification {

    ConsultoriosService consultoriosService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Consultorios(...).save(flush: true, failOnError: true)
        //new Consultorios(...).save(flush: true, failOnError: true)
        //Consultorios consultorios = new Consultorios(...).save(flush: true, failOnError: true)
        //new Consultorios(...).save(flush: true, failOnError: true)
        //new Consultorios(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //consultorios.id
    }

    void "test get"() {
        setupData()

        expect:
        consultoriosService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Consultorios> consultoriosList = consultoriosService.list(max: 2, offset: 2)

        then:
        consultoriosList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        consultoriosService.count() == 5
    }

    void "test delete"() {
        Long consultoriosId = setupData()

        expect:
        consultoriosService.count() == 5

        when:
        consultoriosService.delete(consultoriosId)
        sessionFactory.currentSession.flush()

        then:
        consultoriosService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Consultorios consultorios = new Consultorios()
        consultoriosService.save(consultorios)

        then:
        consultorios.id != null
    }
}
