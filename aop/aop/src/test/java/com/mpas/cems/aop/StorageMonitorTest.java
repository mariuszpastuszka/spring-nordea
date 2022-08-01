package com.mpas.cems.aop;

import com.mpas.cems.aop.config.AopConfig;
import com.mpas.cems.aop.service.StorageService;
import com.mpas.cems.aop.test.TestDbConfig;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AopConfig.class, TestDbConfig.class})
class StorageMonitorTest {

    @Autowired
    StorageService storageService;

    @Test
    void testProxyBubu() {
        var storage = new Storage();
        storage.setId(1L);
        storage.setName("Edinburgh PD Storage");
        storage.setLocation("EH4 3SD");

        var ev1 = new Evidence();
        ev1.setNumber("BL00254");
        ev1.setItemName("Glock 19");
        storage.addEvidence(ev1);

        var ev2 = new Evidence();
        ev2.setNumber("BL00257");
        ev1.setItemName("Bloody bullet 9mm");
        storage.addEvidence(ev2);

        storageService.save(storage);

        var result = storageService.findById(1L);
        assertNotNull(result.get());
    }

    @Test
    void testSaveEvidenceSet() {
        var storage = new Storage();
        storage.setId(1L);
        storage.setName("Edinburgh PD Storage");
        storage.setLocation("EH4 3SD");

        var ev1 = new Evidence();
        ev1.setNumber("BL00254");
        ev1.setItemName("Glock 19");
        storage.addEvidence(ev1);

        var ev2 = new Evidence();
        ev2.setNumber("BL00257");
        ev1.setItemName("Bloody bullet 9mm");
        storage.addEvidence(ev2);

        storageService.saveEvidenceSet(storage);
    }
}
