package com.mpas.cems.fun;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.fun"})
class FunBeanConfig {

    // TODO 13. Add the proper annotation to specify an initialization method and a destroy method
    FunBean funBean() {
        return new FunBean();
    }
}
