
package com.mpas.cems.hib;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.hib.config.AppConfig;
import com.mpas.cems.hib.config.HibernateDbConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateDbConfig.class, AppConfig.class})
@Disabled
 class PersonServiceTest {

    @Autowired
    @Qualifier("personServiceImpl")
    PersonService personService;

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

    @Test
    void testCountPersons(){
        assertEquals(2, personService.countPersons());
    }

    @Test
    void testDeleteById(){
        personService.findById(1L).ifPresentOrElse(
                p -> personService.delete(p),
                () -> fail("Person not found!")
        );
    }
}
