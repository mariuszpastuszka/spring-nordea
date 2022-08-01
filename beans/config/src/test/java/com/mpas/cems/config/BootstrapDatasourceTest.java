package com.mpas.cems.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
public class BootstrapDatasourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testBoot() {
        assertNotNull(dataSource);
    }
}
