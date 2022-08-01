
package com.mpas.cems.emf;

import com.mpas.cems.dao.Person;
import com.mpas.cems.emf.config.AppConfig;
import com.mpas.cems.emf.config.JpaDbConfig;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaDbConfig.class, AppConfig.class})
@Transactional
@Disabled
class JpaPersonRepoTest {

    @Autowired
    @Qualifier("jpaPersonRepo")
    PersonRepo personRepo;

    @Test
    void testFindById() {
        personRepo.findById(1L).ifPresentOrElse(
                p -> assertEquals("sherlock.holmes", p.getUsername()),
                () -> fail("Person not found!")
        );
    }

    @Test
    void testFindAllByLastName() {
        List<Person> persons = personRepo.findAllByLastName("Holmes");
        assertEquals(1,persons.size());
    }

    @Test
    void testNativeQuery(){
        List<String> usernames = personRepo.findAllUsernames();
        assertEquals(2,usernames.size());
    }
}
