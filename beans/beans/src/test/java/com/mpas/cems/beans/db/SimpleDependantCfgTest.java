package com.mpas.cems.beans.db;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SimpleDependantCfgTest {

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(SimpleDependantCfg.class);
        ctx.registerShutdownHook();

        var simpleDependantCfg = ctx.getBean(SimpleDependantCfg.class);
        assertNotNull(simpleDependantCfg);

        var simpleBean = simpleDependantCfg.simpleBean();
        assertNotNull(simpleBean);
    }
}
