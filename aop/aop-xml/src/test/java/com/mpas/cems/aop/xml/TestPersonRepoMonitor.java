package com.mpas.cems.aop.xml;

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
@ContextConfiguration(classes = {TestDbConfig.class})
class TestPersonRepoMonitor {

    @Autowired
    PersonRepo personRepo;

    @Test
    void testFindById() {
        personRepo.findById(1L).ifPresentOrElse(
                p -> assertEquals("sherlock.holmes", p.getUsername()),
                () -> fail("Person not found!")
        );
    }
}
