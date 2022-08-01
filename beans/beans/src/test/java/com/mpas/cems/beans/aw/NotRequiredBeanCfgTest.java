package com.mpas.cems.beans.aw;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class NotRequiredBeanCfgTest {

    @Test
    void testAutowire() {
        var ctx = new AnnotationConfigApplicationContext(NotRequiredBeanCfg.class);
        assertNotNull(ctx);

        BadBean badBean = ctx.getBean(BadBean.class);
        assertNotNull(badBean.getBeanTwo());
        assertNull(badBean.getMissingBean());
    }
}
