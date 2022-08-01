package com.mpas.cems.beans.ci;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


class SimpleAppCfgTest {

    private Logger logger = LoggerFactory.getLogger(SimpleAppCfgTest.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(SimpleAppCfg.class);

        var composedBean = ctx.getBean(ComposedBean.class);
        assertNotNull(composedBean);

        assertNotNull(composedBean.getSimpleBean());

        assertEquals("AB123", composedBean.getCode());
        assertTrue(composedBean.isComplicated());

        var humanBean = ctx.getBean(Human.class);

        assertNotNull(humanBean);
        assertNotNull(humanBean.getItem());
        assertNotNull(humanBean.getItem().getTitle());

        ctx.close();
    }

    @Test
    void testBeanNames() {
        var ctx = new AnnotationConfigApplicationContext(SimpleAppCfg.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName + " of type "
                    + ctx.getBean(beanName).getClass().getSimpleName());
        }

        var simpleBean = ctx.getBean("simpleBeanImpl", SimpleBean.class);
        assertNotNull(simpleBean);
        assertTrue(simpleBean instanceof SimpleBeanImpl);

        ctx.close();
    }
}
