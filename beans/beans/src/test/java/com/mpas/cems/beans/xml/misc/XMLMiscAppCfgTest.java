package com.mpas.cems.beans.xml.misc;

import com.mpas.cems.beans.misc.ScotlandRateFormula;
import com.mpas.cems.beans.misc.SimpleSingleton;
import com.mpas.cems.beans.misc.TaxFormula;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class XMLMiscAppCfgTest {

    @Test
    void testDataSource() {
        var ctx = new ClassPathXmlApplicationContext("classpath:spring/application-misc-cfg.xml");
        assertNotNull(ctx);

        var simpleSingleton = ctx.getBean("simpleSingleton", SimpleSingleton.class);
        assertNotNull(simpleSingleton);

        var simpleSingleton2 = ctx.getBean("simpleSingleton2", SimpleSingleton.class);
        assertNotNull(simpleSingleton2);
        assertEquals(simpleSingleton, simpleSingleton2);

        var taxFormula = ctx.getBean("taxScotlandFormula", TaxFormula.class);
        assertNotNull(taxFormula);
        assertTrue(taxFormula instanceof ScotlandRateFormula);

        var taxFormula2 = ctx.getBean("taxScotlandFormula2", TaxFormula.class);
        assertNotNull(taxFormula2);
        assertTrue(taxFormula2 instanceof ScotlandRateFormula);

        ctx.close();
    }
}
