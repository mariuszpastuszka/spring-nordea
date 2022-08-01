
package com.mpas.cems.jdbc;

import com.mpas.cems.jdbc.config.TestDbConfig;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Disabled  // remove this line to execute the test
// TODO 29. [BONUS] Write test methods to cover all methods in NamedParameterJdbcPersonRepo
@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
class NamedParameterJdbcPersonRepoTest {
    private Logger logger = LoggerFactory.getLogger(JdbcPersonRepoTest.class);

    static final Long PERSON_ID = 1L;

    @Qualifier("jdbcNamedPersonRepo")
    @Autowired
    PersonRepo jdbcNamedPersonRepo;

    @BeforeEach
    void setUp(){
        assertNotNull(jdbcNamedPersonRepo);
    }

    @Test
    void testFindByIdPositive(){
        jdbcNamedPersonRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions:: fail
        );
    }

    @Test
    void testFindByIdNegative(){
        assertThrows( EmptyResultDataAccessException.class, () -> jdbcNamedPersonRepo.findById(99L));
    }

}
