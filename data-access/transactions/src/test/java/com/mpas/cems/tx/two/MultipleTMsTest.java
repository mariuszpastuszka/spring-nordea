package com.mpas.cems.tx.two;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MultipleTransactionManagersConfig.class})
class MultipleTMsTest {

    @Autowired
    TwoTransactionalService service;

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
    void testSimpleBeans() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> service.executeWithinTransaction());
    }
}

