package com.mpas.cems.xml;

import com.mpas.cems.pojos.repos.DetectiveRepo;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ApplicationContextTest {

    @Test
    void testDataSource() {
        var ctx = new ClassPathXmlApplicationContext("classpath:spring/application-cfg-prod.xml");
        assertNotNull(ctx);
        var dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        ctx.registerShutdownHook();
    }

    @Test
    void testJdbcRepo() {
        var ctx = new ClassPathXmlApplicationContext("classpath:application-opt-prod.xml");
        assertNotNull(ctx);
        final DetectiveRepo detectiveRepo = ctx.getBean(DetectiveRepo.class);
        assertNotNull(detectiveRepo);
        assertThrows(NullPointerException.class, () -> detectiveRepo.findById(1L));
        ctx.registerShutdownHook();
    }
}
