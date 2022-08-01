package com.mpas.cems.boot;

import com.mpas.cems.boot.entities.Person;
import com.mpas.cems.boot.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ApplicationTest {

    @Autowired
    private PersonService personService;

    @Test
    void testSavePerson() {
        var person = new Person();
        person.setFirstName("Irene");
        person.setLastName("Adler");
        personService.save(person);

        assertEquals(2, personService.count());
    }

}
