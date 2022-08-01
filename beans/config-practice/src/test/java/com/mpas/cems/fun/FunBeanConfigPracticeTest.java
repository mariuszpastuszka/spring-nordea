package com.mpas.cems.fun;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing bean stages creation for a bean that is initialized using all three techniques
 *
 * @since 1.0
 */
@Disabled  // comment this annotation to run the test
class FunBeanConfigPracticeTest {

    @Test
    void testBeanLifecyclePractice() {
        var ctx = new AnnotationConfigApplicationContext(FunBeanConfig.class);
        ctx.registerShutdownHook();

        var funBean = ctx.getBean(FunBean.class);
        assertNotNull(funBean);
    }
}
