package com.mpas.cems.beans.si;

import com.mpas.cems.beans.ci.SimpleBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AnotherSimpleAppCfgTest {

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(AnotherSimpleAppCfg.class);

        var simpleBean = ctx.getBean(SimpleBean.class);
        assertNotNull(simpleBean);

        var composedBean = ctx.getBean(AnotherComposedBean.class);
        assertNotNull(composedBean);

        assertNotNull(composedBean.getSimpleBean());
        assertTrue(composedBean.isComplex());

        ctx.close();
    }
}
