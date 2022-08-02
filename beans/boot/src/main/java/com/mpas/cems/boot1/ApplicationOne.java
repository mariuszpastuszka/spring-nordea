package com.mpas.cems.boot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class ApplicationOne {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationOne.class, args);
    }
}
