package com.mpas.cems.emf;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.emf.config.AppConfig;
import com.mpas.cems.emf.config.JpaDbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaDbConfig.class, AppConfig.class})
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

}
