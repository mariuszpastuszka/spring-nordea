package com.mpas.cems.emf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.emf.services", "com.mpas.cems.emf.repos"})
@EnableTransactionManagement
public class AppConfig {

}
