package com.mpas.cems.fun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.fun"})
public class FunBeanConfig {

    @Bean(initMethod = "beanInitMethod", destroyMethod = "beanDestroyMethod")
    FunBean funBean() {
        return new FunBean();
    }
}
