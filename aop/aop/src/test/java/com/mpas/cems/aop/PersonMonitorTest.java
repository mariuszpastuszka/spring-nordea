package com.mpas.cems.aop;

import com.mpas.cems.aop.config.AopConfig;
import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.aop.test.TestDbConfig;
import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AopConfig.class, TestDbConfig.class})
class PersonMonitorTest {

    @Autowired
    PersonRepo personRepo;

    @Autowired
    PersonService personService;

    @Test
    void testFindById() {
        personRepo.findById(1L).ifPresentOrElse(
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
    void testSave() {
        var person = new Person();
        person.setId(3L);
        person.setUsername("nancy.drew");
        person.setFirstName("Nancy");
        person.setLastName("Drew");
        person.setPassword("1@#$asta");
        person.setHiringDate(LocalDateTime.now());
        assertNotNull(personService.save(person));
    }

    @Test
    void testBadSave() {
        var person = new Person();
        person.setId(3L);
        person.setUsername("nancy.drew");
        person.setFirstName("Nanc#");
        person.setLastName("&rew");
        person.setPassword("1@#$asta");
        person.setHiringDate(LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

    @Test
    void testBadUpdate() {
        personRepo.findById(1L).ifPresentOrElse(
                p -> assertThrows(IllegalArgumentException.class, () -> personService.updateFirstName(p, "Sh$r1oc#")),
                () -> fail("Person not found!")
        );
    }

}
