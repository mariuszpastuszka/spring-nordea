
package com.mpas.cems.tx.one;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MultipleTransactionManagersConfig.class})
class MultipleTMsTest {

    @Autowired
    OneTransactionalService service;

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads(){
        assertNotNull(context);
        PlatformTransactionManager t1 = context.getBean("txManager", PlatformTransactionManager.class);
        PlatformTransactionManager t2 = context.getBean("simpleManager", PlatformTransactionManager.class);
        assertNotNull(t1);
        assertNotNull(t2);
    }

    @Test
    void testSimpleManagerExecution() {
        service.executeWithSimpleManager();
    }

    @Test
    void testTxManagerExecution() {
        service.executeWithTxManager();
    }
}
