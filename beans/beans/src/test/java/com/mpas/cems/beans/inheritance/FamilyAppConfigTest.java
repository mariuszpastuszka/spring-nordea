package com.mpas.cems.beans.inheritance;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class FamilyAppConfigTest {

    private Logger logger = LoggerFactory.getLogger(FamilyAppConfigTest.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(FamilyAppConfig.class);

        var parentBean = ctx.getBean("parentBean", ParentBean.class);
        assertNotNull(parentBean);

        var childBean = ctx.getBean("childBean", ChildBean.class);
        assertNotNull(childBean);

        assertEquals(parentBean.getFamilyName(), childBean.getFamilyName());
        assertNotEquals(parentBean.getSurname(), childBean.getSurname());

        logger.debug(parentBean.toString());
        logger.debug(childBean.toString());
        ctx.close();
    }

}
