
package com.mpas.cems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BootSecureApp {

    private static Logger logger = LoggerFactory.getLogger(BootSecureApp.class);

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BootSecureApp.class, args);
        ctx.registerShutdownHook();
        logger.info("Application Started ...");
    }
}
