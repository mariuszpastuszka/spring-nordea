package com.mpas.cems.aop.within;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.aop.test.TestDbConfig;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDbConfig.class, WithinConfig.class})
public class WithinDesignatorTest {

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
    void testFindByCompleteName() {
        personService.findByCompleteName("Sherlock", "Holmes").ifPresent(person ->
                assertEquals("sherlock.holmes", person.getUsername())
        );
    }

}
