package com.mpas.cems.beans.xml.si;

import com.mpas.cems.beans.si.AnotherComposedBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AnotherSimpleAppCfgTest {
    @Test
    void testDataSource() {
        var ctx = new ClassPathXmlApplicationContext("classpath:spring/application-si-cfg.xml");
        assertNotNull(ctx);

        var composedBean = ctx.getBean(AnotherComposedBean.class);
        assertNotNull(composedBean);
        assertNotNull(composedBean.getSimpleBean());
        assertTrue(composedBean.isComplex());

        ctx.registerShutdownHook();
    }
}
