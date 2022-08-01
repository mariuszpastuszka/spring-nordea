
package com.mpas.cems.dj;

import com.mpas.cems.dj.config.DataSourceConfig;
import com.mpas.cems.dj.services.DetectiveService;
import com.mpas.cems.dj.services.PersonService;

import com.mpas.cems.dj.services.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class, ServiceConfig.class})
 class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    StorageService storageService;

    @Autowired
    DetectiveService detectiveService;

    @Test
    void testFindById() {
        // because 1L is the Storage
        personService.findById(2L).ifPresentOrElse(
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
