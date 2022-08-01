
package com.mpas.cems.boot;

import com.mpas.cems.boot.dao.Person;
import com.mpas.cems.boot.repos.PersonRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class RepositoriesTest {

    @Autowired
    PersonRepo personRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testsavePerson(){
        var person = new  Person();
        person.setUsername("irene.adler");
        person.setFirstName("Irene");
        person.setLastName("Adler");
        person.setHiringDate(LocalDateTime.now());
        person.setPassword("a12sd");

        var savedPerson = personRepo.save(person);

        assertAll(
                () ->   assertNotNull(savedPerson.getId()),
                () ->   assertNotNull(savedPerson.getCreatedAt()),
                () ->   assertEquals(person,savedPerson)
        );
    }

    @Test
    void testFindByCompleteName() {
        Person person = new  Person();
        person.setUsername("irene.adler");
        person.setFirstName("Irene");
        person.setLastName("Adler");
        person.setHiringDate(LocalDateTime.now());
        person.setPassword("a12sd");

        entityManager.persist(person);
        entityManager.flush();

        personRepo.findByCompleteName("Irene", "Adler").ifPresentOrElse(
                foundPerson -> assertEquals(person, foundPerson),
                () -> fail("Person not found!")
        );
    }
}
