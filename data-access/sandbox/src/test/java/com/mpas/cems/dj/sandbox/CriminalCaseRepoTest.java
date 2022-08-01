
package com.mpas.cems.dj.sandbox;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dj.sandbox.config.JpaConfig;
import com.mpas.cems.dj.sandbox.repos.AppConfig;
import com.mpas.cems.dj.sandbox.repos.cc.CriminalCaseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfig.class, AppConfig.class})
@Transactional
public class CriminalCaseRepoTest {
    private Logger logger = LoggerFactory.getLogger(CriminalCaseRepoTest.class);

    @Autowired
    CriminalCaseRepo criminalCaseRepo;

    @Test
    void testTeamForCase(){
        criminalCaseRepo.findByCaseNumber("OM51710783").ifPresentOrElse(
                criminalCase ->  {
                    List<Detective> team = criminalCaseRepo.getTeam(criminalCase);
                    team.forEach(d -> logger.info("Working on the case: {}", d));
                },
                Assertions::fail
        );

    }
}
