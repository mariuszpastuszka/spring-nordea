package com.mpas.cems.beans.naming;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AliasesCfg {

    @Bean(name = {"beanOne", "beanTwo"})
    SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }
}
