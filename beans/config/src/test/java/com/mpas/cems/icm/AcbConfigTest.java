package com.mpas.cems.icm;

import com.mpas.cems.im.AcbConfig;
import com.mpas.cems.im.AnotherComplexBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AcbConfigTest {

    @Test
    void testInitializationBean() {
        var ctx = new AnnotationConfigApplicationContext(AcbConfig.class);
        ctx.registerShutdownHook();

        var complexBean = ctx.getBean(AnotherComplexBean.class);
        assertNotNull(complexBean);
    }
}
