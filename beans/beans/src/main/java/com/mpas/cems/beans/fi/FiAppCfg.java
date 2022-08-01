package com.mpas.cems.beans.fi;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.beans.fi"})
public class FiAppCfg {

    @Bean
    SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }
}
