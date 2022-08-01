
package com.mpas.cems.dj.sandbox;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.dj.sandbox.config.JpaConfig;
import com.mpas.cems.dj.sandbox.repos.AppConfig;
import com.mpas.cems.dj.sandbox.repos.StorageRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfig.class, AppConfig.class})
@Transactional
class StorageRepoTest {
    private Logger logger = LoggerFactory.getLogger(StorageRepoTest.class);

    @Autowired
    StorageRepo storageRepo;

    @Test
    void testFindAllPaginated(){
        int pageNo = 3;
        int pageSize = 2;

       Page<Storage> page = storageRepo.findAll(PageRequest.of(pageNo,pageSize));

       assertAll(
               () -> assertEquals(7, page.getTotalPages()),
               () -> assertEquals(13, page.getTotalElements()),
               () -> assertEquals(3, page.getNumber()),
               () -> assertEquals(2, page.getNumberOfElements())
       );

       page.getContent().forEach(s -> logger.info("Storage: {}", s));
    }
}
