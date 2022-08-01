package com.mpas.cems.config;

import com.mpas.cems.pojos.repos.EvidenceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ProdDataSourceConfig.class, TestProfileDataSourceConfig.class, RepositoryConfig.class})
public class ProfilesTest {

    @Autowired
    EvidenceRepo evidenceRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void testInjectedBeans() {
        assertTrue(dataSource instanceof DriverManagerDataSource);
        assertNotNull(evidenceRepo);
    }

}

