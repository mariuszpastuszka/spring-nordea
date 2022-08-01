package com.mpas.cems.im;

import com.mpas.cems.lc.SimpleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.im"})
public class AcbConfig {

    @Bean(initMethod = "beanInitMethod", destroyMethod = "beanDestroyMethod")
    AnotherComplexBean anotherComplexBean() {
        return new AnotherComplexBean();
    }

    @Bean
    SimpleBean simpleBean() {
        return new SimpleBean();
    }
}
