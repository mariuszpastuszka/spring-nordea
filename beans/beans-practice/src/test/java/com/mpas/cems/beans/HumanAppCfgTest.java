package com.mpas.cems.beans;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


// Comment the @Disabled annotation to run your test
@Disabled
class HumanAppCfgTest {

    @Test
    void testHumanAndItem() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(HumanAppCfg.class);

        Human humanBean = ctx.getBean(Human.class);

        assertNotNull(humanBean);
        assertNotNull(humanBean.getItem());
        assertNotNull(humanBean.getItem().getTitle());

        ctx.close();
    }
}
