package com.mpas.cems.config;

import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


// TODO 11. Modify this test class to use more than one configuration class
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FullConfig.class})
public class FullConfigTest {

    @Autowired
    EvidenceRepo evidenceRepo;

    @Autowired
    DetectiveRepo detectiveRepo;

    @Test
    public void testInjectedBeans() {
        assertNotNull(evidenceRepo);
        assertNotNull(detectiveRepo);
    }

}
