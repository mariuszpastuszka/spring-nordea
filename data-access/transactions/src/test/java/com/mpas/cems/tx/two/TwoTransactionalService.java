
package com.mpas.cems.tx.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class TwoTransactionalService {
    private Logger logger = LoggerFactory.getLogger(TwoTransactionalService.class);

    @Transactional
    public void executeWithinTransaction() {
        logger.info("Executing TransactionalService.executeWithinTransaction.");
    }

}
