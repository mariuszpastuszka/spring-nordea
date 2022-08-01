package com.mpas.cems.simple;

import com.mpas.cems.beans.ci.SimpleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


class OneSimpleConfigTest {

    private Logger logger = LoggerFactory.getLogger(OneSimpleConfigTest.class);

    @Test
    void testSimpleConfiguration() {
        var ctx =
                new AnnotationConfigApplicationContext(SimpleConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            logger.info("Bean " + beanName);
        }
    }

    @Test
    void testOneBeanConfiguration() {
        var ctx =
                new AnnotationConfigApplicationContext(OneBeanConfig.class);

        var simpleBeanOne = ctx.getBean(SimpleBean.class);
        var simpleBeanTwo = ctx.getBean(SimpleBean.class);
        Assertions.assertEquals(simpleBeanTwo, simpleBeanOne);
    }
}
