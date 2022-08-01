
package com.mpas.cems.boot.services;

import com.mpas.cems.boot.dao.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Service
public class Initializer {

    private Logger logger = LoggerFactory.getLogger(Initializer.class);
    private PersonService personService;

    @Autowired
    public Initializer(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void init() {
        logger.info(" -->> Starting database initialization...");
        var person = new Person();
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
