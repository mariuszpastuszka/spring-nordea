package com.mpas.cems.config;

import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
// The following config only works if the @Profile("prod") is removed from ProdDataSourceConfig class
//@ContextConfiguration(classes = {ProdDataSourceConfig.class, RepositoryConfig.class})
@ContextConfiguration(classes = {EnvTestDataSourceConfig.class, RepositoryConfig.class})
public class MultipleResourcesTest {

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
