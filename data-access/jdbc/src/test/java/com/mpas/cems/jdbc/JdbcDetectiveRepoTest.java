package com.mpas.cems.jdbc;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.jdbc.config.TestDbConfig;
import com.mpas.cems.repos.DetectiveRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
class JdbcDetectiveRepoTest {
    private Logger logger = LoggerFactory.getLogger(JdbcDetectiveRepoTest.class);

    static final Long DETECTIVE_ID = 1L;

    @Autowired
    DetectiveRepo detectiveRepo;

    @Test
    void testFindByIdWithDetails() {
        Optional<Detective> detective = detectiveRepo.findByIdWithPersonDetails(DETECTIVE_ID);
        detective.ifPresentOrElse(
                d -> assertNotNull(d.getPerson()),
                Assert::fail
        );
        logger.info("Result: {}", detective);
    }
}
