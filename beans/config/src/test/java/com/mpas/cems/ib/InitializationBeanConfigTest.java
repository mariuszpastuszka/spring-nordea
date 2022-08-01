package com.mpas.cems.ib;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing stages of bean creation for a bean of a type implementing InitializingBean
 *
 * @since 1.0
 */
public class InitializationBeanConfigTest {

    @Test
    void testInitializationBean() {
        var ctx = new AnnotationConfigApplicationContext(InitializationBeanConfig.class);
        ctx.registerShutdownHook();

        var complexBean = ctx.getBean(ComplexBean.class);
        assertNotNull(complexBean);
    }
}
