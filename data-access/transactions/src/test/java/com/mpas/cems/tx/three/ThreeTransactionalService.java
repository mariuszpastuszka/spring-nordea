
package com.mpas.cems.tx.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ThreeTransactionalService {
    private Logger logger = LoggerFactory.getLogger(ThreeTransactionalService.class);

    @Transactional
    public void executeWithTxManager() {
        logger.info("Executing OneTransactionalService.executeWithTxManager.");
    }


    @Transactional(transactionManager = "simpleManager", readOnly = true)
    public void executeWithSimpleManager() {
        logger.info("Executing OneTransactionalService.executeWithSimpleManager.");
    }

}
