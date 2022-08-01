package com.mpas.cems.scopes2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.scopes2"})
public class AppConfig {

    @Bean
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
    @SalaryScope
    SalaryIdea salary() {
        return new Salary();
    }
}
