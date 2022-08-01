package com.mpas.cems.boot3;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mpas.cems.BeanManager.asHtml;


@RestController
@SpringBootApplication
public class ApplicationThree implements ApplicationContextAware {
    ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationThree.class, args);
    }

    @GetMapping("/")
    public String index() {
        return asHtml.apply(ctx);
    }

}
