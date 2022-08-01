
package com.mpas.cems.hib;

import com.mpas.cems.hib.config.AppConfig;
import com.mpas.cems.hib.config.HibernateDbConfig;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateDbConfig.class, AppConfig.class})
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
    void testFindByUsername(){
        personRepo.findByUsername("sherlock.holmes").ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions:: fail
        );

    }

}
