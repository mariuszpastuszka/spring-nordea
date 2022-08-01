package com.mpas.cems.beans.fi;

import com.mpas.cems.beans.ci.ComposedBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class FiAppCfgTest {
    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(FiAppCfg.class);
        var composedBean = ctx.getBean(ComposedBean.class);
        assertNotNull(composedBean);
        assertNotNull(composedBean.getSimpleBean());
        assertEquals("AB123", composedBean.getCode());
        assertTrue(composedBean.isComplicated());
        ctx.close();
    }
}
