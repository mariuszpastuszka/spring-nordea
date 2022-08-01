package com.mpas.cems.beans.xml.ci;

import com.mpas.cems.beans.ci.ComposedBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


class SimpleAppCfgTest {

    @Test
    void testDataSource() {
        var ctx = new ClassPathXmlApplicationContext("classpath:spring/application-ci-cfg.xml");
        assertNotNull(ctx);

        var composedBean = ctx.getBean(ComposedBean.class);
        assertNotNull(composedBean);
        assertNotNull(composedBean.getSimpleBean());
        assertEquals("AB123", composedBean.getCode());
        assertTrue(composedBean.isComplicated());

        ctx.registerShutdownHook();
    }
}
