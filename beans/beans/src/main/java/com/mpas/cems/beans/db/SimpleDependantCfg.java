package com.mpas.cems.beans.db;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;


@Configuration
public class SimpleDependantCfg {

    private Logger logger = LoggerFactory.getLogger(SimpleDependantCfg.class);

    @Bean
    SimpleBean simpleBean() {
        logger.info("---> Creating 'simpleBean' ");
        return new SimpleBeanImpl();
    }

    @Bean
    @Description("This bean depends on 'simpleBean'")
    DependantBean dependantBean() {
        return new DependantBeanImpl(simpleBean());
    }
}
