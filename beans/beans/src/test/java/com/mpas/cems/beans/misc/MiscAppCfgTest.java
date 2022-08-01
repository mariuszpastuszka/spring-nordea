package com.mpas.cems.beans.misc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class MiscAppCfgTest {
    private Logger logger = LoggerFactory.getLogger(MiscAppCfgTest.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(MiscAppCfg.class);

        var simpleSingleton = ctx.getBean(SimpleSingleton.class);
        assertNotNull(simpleSingleton);

        var simpleSingleton2 = ctx.getBean("simpleSingleton", SimpleSingleton.class);
        assertNotNull(simpleSingleton2);
        assertEquals(simpleSingleton, simpleSingleton2);

        var taxFormula = ctx.getBean("taxScotlandFormula", TaxFormula.class);
        assertNotNull(taxFormula);
        assertTrue(taxFormula instanceof ScotlandRateFormula);
        ctx.close();
    }
}
