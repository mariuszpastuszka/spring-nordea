
package com.mpas.cems.emf.services;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class Initializer {

    private Logger logger = LoggerFactory.getLogger(Initializer.class);
    private PersonService personService;

    public Initializer(@Qualifier("personServiceImpl") PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void init() {
        logger.info(" -->> Starting database initialization...");
        Person person = new Person();
        person.setUsername("sherlock.holmes");
        person.setFirstName("Sherlock");
        person.setLastName("Holmes");
        person.setPassword("dudu");
        person.setHiringDate(LocalDateTime.now());
        personService.save(person);

        person = new Person();
        person.setUsername("jackson.brodie");
        person.setFirstName("Jackson");
        person.setLastName("Brodie");
        person.setPassword("bagy");
        person.setHiringDate(LocalDateTime.now());
        personService.save(person);
        logger.info(" -->> Database initialization finished.");
    }
}
