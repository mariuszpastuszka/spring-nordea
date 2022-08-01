package com.mpas.cems.config;

import com.mpas.cems.repos.JdbcDetectiveRepo;
import com.mpas.cems.repos.JdbcEvidenceRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestDataSourceConfigTest {

    private Logger logger = LoggerFactory.getLogger(TestDataSourceConfigTest.class);

    @Test
    void testMultipleCfgSource() {
        var ctx =
                new AnnotationConfigApplicationContext(TestDataSourceConfig.class, RepositoryConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type "
                    + ctx.getBean(beanName).getClass().getSimpleName());
        }

        var evidenceRepo = ctx.getBean(JdbcEvidenceRepo.class);
        var detectiveRepo = ctx.getBean(JdbcDetectiveRepo.class);

        assertNotNull(evidenceRepo);
        assertNotNull(detectiveRepo);

        var dataSource = ctx.getBean("two", DataSource.class);
        assertNotNull(dataSource);
    }
}