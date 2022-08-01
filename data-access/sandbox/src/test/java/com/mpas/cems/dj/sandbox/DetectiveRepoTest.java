
package com.mpas.cems.dj.sandbox;

import com.mpas.cems.dj.sandbox.config.JpaConfig;
import com.mpas.cems.dj.sandbox.repos.AppConfig;
import com.mpas.cems.dj.sandbox.repos.DetectiveRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfig.class, AppConfig.class})
@Transactional
class DetectiveRepoTest {

    @Autowired
    DetectiveRepo detectiveRepo;

    @Test
    void testFindById() {
        assertEquals(2, detectiveRepo.findAll().size());
    }

}
