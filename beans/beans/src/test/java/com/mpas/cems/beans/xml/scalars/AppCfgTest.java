package com.mpas.cems.beans.xml.scalars;

import com.mpas.cems.beans.scalars.PersonBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AppCfgTest {
    @Test
    void testDataSource() {
        var ctx = new ClassPathXmlApplicationContext("classpath:spring/application-scalars-cfg.xml");
        assertNotNull(ctx);

        var pb = ctx.getBean(PersonBean.class);
        assertNotNull(pb);
        System.out.println(pb.toString());

        ctx.registerShutdownHook();
    }
}
