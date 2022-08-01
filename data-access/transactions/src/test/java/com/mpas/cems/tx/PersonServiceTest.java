
package com.mpas.cems.tx;

import com.mpas.cems.aop.exception.MailSendingException;
import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.tx.config.AppConfig;
import com.mpas.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
 class PersonServiceTest {
    private Logger logger = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    PersonService personService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @SqlGroup({
            @Sql(value = "classpath:test/extra-data.sql", config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--")),
            @Sql(
                    scripts = "classpath:test/delete-test-data.sql",
                    config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
            )
    })
     void testCount() {
        long count = personService.countPersons();
        assertEquals(4, count);
    }

    @Test
    @Sql(statements = {"drop table NEW_PERSON if exists;"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateTable(){
        jdbcTemplate.execute("create table NEW_PERSON(" +
                "  ID BIGINT IDENTITY PRIMARY KEY " +
                ", USERNAME VARCHAR2(50) NOT NULL " +
                ", FIRSTNAME VARCHAR2(50) " +
                ", LASTNAME VARCHAR2(50) " +
                ", UNIQUE(USERNAME)) ");
        long result = jdbcTemplate.queryForObject("select count(*) from NEW_PERSON", Long.class);
        // table exists but is empty
        assertEquals(0, result);
    }

    @Test
    void testUpdatePassword() {
        personService.findById(2L).ifPresent(
                p ->
                        assertThrows(
                                MailSendingException.class,
                                () -> personService.updatePassword(p, "test_pass")
                        )
        );
    }

    @Test
    void testUpdateUsername() {
        personService.findById(1L).ifPresent(
                p ->
                        assertThrows(
                                DataIntegrityViolationException.class,
                                () -> personService.updateUsername(p, "irene.adler")
                        )
        );
        //making sure rollback did not affect anything
        personService.findById(1L).ifPresent(p -> logger.info("->> {}" , p.toString()));
    }
}
