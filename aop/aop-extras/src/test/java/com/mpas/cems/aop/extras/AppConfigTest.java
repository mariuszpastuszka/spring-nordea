package com.mpas.cems.aop.extras;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppConfigTest {
    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        CallerBean callerBean = ctx.getBean(CallerBean.class);

        callerBean.doStuff();
    }
}
