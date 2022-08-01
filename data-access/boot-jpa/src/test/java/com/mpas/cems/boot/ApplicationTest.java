
package com.mpas.cems.boot;

import com.mpas.cems.boot.services.PersonService;
import com.mpas.cems.boot.dao.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class ApplicationTest {

    @Autowired
    private PersonService personService;

    @Test
    void testSavePerson(){
        var person = new Person();
        person.setUsername("irene.adler");
        person.setFirstName("Irene");
        person.setLastName("Adler");
        person.setHiringDate(LocalDateTime.now());
        person.setPassword("a12sd");
        personService.save(person);

        assertEquals(3, personService.count());
    }

    @Test
    void testFindById() {
        personService.findById(1L).ifPresentOrElse(
                p -> assertEquals("sherlock.holmes", p.getUsername()),
                () -> fail("Person not found!")
        );
    }

    @Test
    void testfindByCompleteName() {
        personService.findByCompleteName("Sherlock", "Holmes").ifPresent(person ->
                assertEquals("sherlock.holmes", person.getUsername())
        );
    }

    @Test
    void testFindAll() {
        assertNotNull(personService.findAll());
    }

}
