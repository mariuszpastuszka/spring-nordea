package com.mpas.cems.beans.scalars;

import com.mpas.cems.beans.ci.SimpleBean;
import com.mpas.cems.beans.ci.SimpleBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import java.util.*;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.beans.scalars"})
public class AppConvertersCfg {

    @Autowired
    StringToLocalDate stringToLocalDateConverter;

    @Autowired
    StringToDate stringToDate;

    @Bean
    ConversionService conversionService(ConversionServiceFactoryBean factory) {
        return factory.getObject();
    }

    @Bean
    ConversionServiceFactoryBean conversionServiceFactoryBean() {
        var conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Set.of(stringToLocalDateConverter, stringToDate));
        return conversionServiceFactoryBean;
    }

    @Bean
    List<SimpleBean> simpleBeanList() {
        return new ArrayList<>();
    }

    @Bean
    Set<SimpleBean> simpleBeanSet() {
        return new HashSet<>();
    }

    @Bean
    Map<String, SimpleBean> simpleBeanMap() {
        return new HashMap<>();
    }

    @Bean
    SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }

    @Bean
    List<SimpleBean> simpleBeanList2() {
        return List.of(simpleBean());
    }

    @Bean
    Set<SimpleBean> simpleBeanSet2() {
        return Set.of(simpleBean());
    }

    @Bean
    Map<String, SimpleBean> simpleBeanMap2() {
        return Map.of("simpleBean", simpleBean());
    }
}
