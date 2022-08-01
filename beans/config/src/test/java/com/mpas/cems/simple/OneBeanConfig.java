package com.mpas.cems.simple;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OneBeanConfig {

    @Bean
    SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }
}
