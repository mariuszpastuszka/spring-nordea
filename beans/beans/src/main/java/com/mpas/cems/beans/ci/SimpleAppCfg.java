package com.mpas.cems.beans.ci;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.beans.ci"})
public class SimpleAppCfg {

    // de-comment this to cause NoUniqueBeanDefinitionException
   /* @Bean
    SimpleBean anotherSimpleBean(){
        return new SimpleBeanImpl();
    }*/
}
