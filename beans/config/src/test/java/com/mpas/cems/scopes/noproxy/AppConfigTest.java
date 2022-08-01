package com.mpas.cems.scopes.noproxy;

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
        logger.info("Salary from from Employee: {}", salary.getAmount());
        logger.info("Salary from from Employee: {}", employee.getSalary().getAmount());
        logger.info("Salary bean from Employee actual type: {}", salary.getClass().toString());

        logger.info("Salary from context: {}", ctx.getBean(Salary.class).getAmount());
        logger.info("Salary from context: {}", ctx.getBean(Salary.class).getAmount());
        logger.info("Salary from context : {}", ctx.getBean(Salary.class).getAmount());

    }
}
