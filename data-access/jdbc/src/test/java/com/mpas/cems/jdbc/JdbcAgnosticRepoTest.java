package com.mpas.cems.jdbc;

import com.mpas.cems.jdbc.config.TestDbConfig;
import com.mpas.cems.jdbc.repos.AgnosticRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
class JdbcAgnosticRepoTest {

    @Autowired
    AgnosticRepo agnosticRepo;

    @Test
    void testCreateTable() {
        int result = agnosticRepo.createTable("new_storage");
        // table exists but is empty
        assertEquals(0, result);
    }
}
