
package com.mpas.cems.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class PracticeRestApplication {

    private static Logger logger = LoggerFactory.getLogger(PracticeRestApplication.class);

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PracticeRestApplication.class, args);
        ctx.registerShutdownHook();
        logger.info("Application Started ...");
    }
}
