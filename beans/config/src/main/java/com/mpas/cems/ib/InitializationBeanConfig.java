package com.mpas.cems.ib;

import com.mpas.cems.lc.AnotherSimpleBean;
import com.mpas.cems.lc.SimpleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.ib"})
public class InitializationBeanConfig {

    @Bean
    SimpleBean simpleBean() {
        return new SimpleBean();
    }

    @Bean
    AnotherSimpleBean anotherSimpleBean() {
        return new AnotherSimpleBean();
    }
}
