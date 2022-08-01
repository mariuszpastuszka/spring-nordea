
package com.mpas.cems.hib.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.hib.services", "com.mpas.cems.hib.repos"})
@EnableTransactionManagement
public class AppConfig {

}
