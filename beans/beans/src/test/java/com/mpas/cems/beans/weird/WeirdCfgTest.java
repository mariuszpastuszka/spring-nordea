package com.mpas.cems.beans.weird;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class WeirdCfgTest {

    @Test
    void testAutowire() {
        var ctx = new AnnotationConfigApplicationContext(WeirdCfg.class);
        assertNotNull(ctx);

        var weirdBean = ctx.getBean(WeirdBean.class);
        assertNotNull(weirdBean);
        assertEquals("this is it", weirdBean.getName());
    }
}
