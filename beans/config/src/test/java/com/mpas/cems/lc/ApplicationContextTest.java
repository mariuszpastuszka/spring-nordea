package com.mpas.cems.lc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing application context lifecycle.BeanFactoryPostProcessor
 *
 * @since 1.0
 */
class ApplicationContextTest {
    private Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(DataSourceCfg.class);
        ctx.registerShutdownHook();
        logger.info(" >> init done.");

        var dataSource = ctx.getBean(DataSource.class);
        assertNotNull(dataSource);

        logger.info(" >> usage done.");
    }

    @Test
    void testBeanLifecycle() {
        var ctx = new AnnotationConfigApplicationContext(SimpleConfig.class);
        ctx.registerShutdownHook();

        var complexBean = ctx.getBean(ComplexBean.class);
        assertNotNull(complexBean);
    }
}
