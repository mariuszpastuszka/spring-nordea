package com.mpas.cems.scopes.proxy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AppConfigTest {
    private Logger logger = LoggerFactory.getLogger(AppConfigTest.class);

    @Test
    void testBeanLifecycle() {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();

        var employee = ctx.getBean(Employee.class);
        assertNotNull(employee);

        var salary = employee.getSalary();
        assertNotNull(salary);
        logger.info("Salary bean actual type: {}", salary.getClass().toString());

        logger.info("Salary: {}", salary.getAmount());
        logger.info("Salary: {}", salary.getAmount());
        logger.info("Salary: {}", salary.getAmount());

    }
}
