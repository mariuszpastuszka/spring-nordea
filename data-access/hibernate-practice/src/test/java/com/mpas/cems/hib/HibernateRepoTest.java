
package com.mpas.cems.hib;

import com.mpas.cems.dao.Person;
import com.mpas.cems.hib.config.AppConfig;
import com.mpas.cems.hib.config.HibernateDbConfig;
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
@ContextConfiguration(classes = {HibernateDbConfig.class, AppConfig.class})
@Transactional
@Disabled
class HibernateRepoTest {

    @Autowired
    @Qualifier("hibernatePersonRepo")
    PersonRepo personRepo;

    @Test
    void testNativeQuery(){
        List<String> usernames = personRepo.findAllUsernames();
        assertEquals(2,usernames.size());
    }

    @Test
    void testFindAllByLastName() {
        List<Person> persons = personRepo.findAllByLastName("Holmes");
        assertEquals(1,persons.size());
    }

    @Test
    void testCountPersons(){
        assertEquals(2, personRepo.count());
    }

    @Test
    void testDeleteById(){
        personRepo.deleteById(1L);

        personRepo.findById(1L).ifPresent(p -> fail("Person found!"));
    }
}
