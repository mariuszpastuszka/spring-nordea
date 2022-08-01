package com.mpas.cems.jdbc;

import com.mpas.cems.dao.Person;
import com.mpas.cems.jdbc.config.TestDbConfig;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
class JdbcPersonRepoTest {

    private Logger logger = LoggerFactory.getLogger(JdbcPersonRepoTest.class);

    static final Long PERSON_ID = 1L;

    @Autowired
    @Qualifier("extraJdbcPersonRepo")
    PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        assertNotNull(personRepo);
    }

    @Test
    void testFindByIdPositive() {
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions::fail
        );
    }

    @Test
    public void testUpdate() {
        int result = personRepo.updatePassword(1L, "newpass");
        assertEquals(1, result);
    }

    @Test
    void testFindByIdNegative() {
        assertThrows(EmptyResultDataAccessException.class, () -> personRepo.findById(99L));
    }

    @Test
    void testFindAll() {
        Set<Person> personSet = personRepo.findAll();
        assertNotNull(personSet);
        assertEquals(2, personSet.size());
    }

    @Test
    void testFindAllByUsernamePart() {
        Set<Person> personSet = personRepo.findAllByUsernamePart("sherlock");
        assertNotNull(personSet);
        assertEquals(1, personSet.size());
    }


    @Test
    void testFindAsMap() {
        Map<String, Object> result = personRepo.findByIdAsMap(PERSON_ID);
        assertEquals(9, result.size());
        logger.info("Res: {}", result);
    }

    @Test
    void testFindAsList() {
        List<Map<String, Object>> result = personRepo.findAllAsMaps();
        assertEquals(2, result.size());
        logger.info("Res: {}", result);
    }

    @Test
    void testCreatePerson() {
        int result = personRepo.createPerson(3L, "chloe.decker", "Chloe", "Decker", "m0rn1ngstar");
        assertEquals(1, result);

        Optional<Person> personOpt = personRepo.findByUsername("chloe.decker");
        personOpt.ifPresentOrElse(p -> assertNotNull(p.getId()),
                Assertions::fail);
    }

    @Test
    void testPrintHtml() {
        personRepo.htmlAllByName("sherlock.holmes");
    }

    @AfterEach
    void cleanUp() {
        try {
            personRepo.findByUsername("chloe.decker").ifPresent(p -> personRepo.delete(p));
        } catch (EmptyResultDataAccessException e) {
            // do nothing we expect this
        }
    }
}
