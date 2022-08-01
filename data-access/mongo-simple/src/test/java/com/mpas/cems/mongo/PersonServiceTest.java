
package com.mpas.cems.mongo;

import com.mpas.cems.mongo.config.AppConfig;
import com.mpas.cems.mongo.dao.Person;
import com.mpas.cems.mongo.services.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class})
@Disabled
class PersonServiceTest {

    private Logger logger = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    PersonService personService;

    @BeforeEach
    void setUp(){
        assertNotNull(personService);
        init();
    }

    @AfterEach
    void tearDown(){
       personService.deleteAll();
    }

    @Test
    void testFindByLastName(){
       List<Person> persons = personService.findByLastName("Holmes");
       assertEquals(1, persons.size());
    }

    @Test
    void testFindByUsername(){
        Person person = personService.findByUsername("sherlock.holmes");
        assertNotNull(person);
        logger.info("Sherlock {}" , person);
    }

    @Test
    void testFindAll() {
        List<Person> persons = personService.findAll();
        assertEquals(2, persons.size());
    }

    void init() {
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
