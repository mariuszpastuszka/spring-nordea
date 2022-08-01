package com.mpas.cems.beans.weird;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan("com.mpas.cems.beans.weird")
@Configuration
public class WeirdCfg {

    @Bean
    String name() {
        return "this is it";
    }
}
