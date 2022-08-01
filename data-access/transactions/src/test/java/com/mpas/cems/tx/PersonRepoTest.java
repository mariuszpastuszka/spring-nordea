
package com.mpas.cems.tx;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import com.mpas.cems.tx.config.AppConfig;
import com.mpas.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
@Rollback(false)
@Disabled // De-Comment this annotation and run to see the tests fail
class PersonRepoTest {

    @Autowired
    PersonRepo repo;

    //@Commit
    @Test
    @Transactional
    void testCreatePerson(){
        Person person = new Person();
        person.setId(99L);
        person.setUsername("test.user");
        person.setFirstName("test");
        person.setLastName("user");
        person.setPassword("password");
        person.setHiringDate(LocalDateTime.now());
        person.setCreatedAt(LocalDateTime.now());
        person.setModifiedAt(LocalDateTime.now());
        repo.save(person);
    }

    // this will fail
    @Test
    @Rollback
    void testCountPersons(){
        long result = repo.count();
        assertEquals(2, result);
    }

}
