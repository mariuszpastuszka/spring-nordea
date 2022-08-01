package com.mpas.cems.beans.naming;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class AliasesCfgTest {

    private Logger logger = LoggerFactory.getLogger(AliasesCfg.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(AliasesCfg.class);

        var simpleBean = ctx.getBean("beanOne", SimpleBean.class);
        assertNotNull(simpleBean);
        assertTrue(simpleBean instanceof SimpleBeanImpl);

        var simpleBean2 = ctx.getBean("beanTwo", SimpleBean.class);
        assertEquals(simpleBean2, simpleBean);

        // no bean named 'simpleBean'
        assertThrows(NoSuchBeanDefinitionException.class, () -> ctx.getBean("simpleBean", SimpleBean.class));

        ctx.close();
    }
}
