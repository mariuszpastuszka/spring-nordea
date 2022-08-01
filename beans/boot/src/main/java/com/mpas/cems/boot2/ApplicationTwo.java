package com.mpas.cems.boot2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import static com.mpas.cems.BeanManager.asHtml;


//@RestController
//@SpringBootApplication
public class ApplicationTwo {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTwo.class, args);
    }

    //@Autowired
    ApplicationContext ctx;

    //@GetMapping("/")
    public String index() {
        return asHtml.apply(ctx);
    }


}
