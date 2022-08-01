package com.mpas.cems.repos2;

import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RepoConfig.class})
public class TestRepoCfg {

    @Qualifier("jdbcCriminalCaseRepo")
    @Autowired
    CriminalCaseRepo jdbcRepo;

    @Qualifier("JPACriminalCaseRepo")
    @Autowired
    CriminalCaseRepo jpaRepo;

    @Test
    void allGoodTest() {
        assertNotEquals(jdbcRepo, jpaRepo);
    }
}
