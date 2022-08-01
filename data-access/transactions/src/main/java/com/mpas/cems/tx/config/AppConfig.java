
package com.mpas.cems.tx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.repos", "com.mpas.cems.tx.services"})
@EnableTransactionManagement
public class AppConfig {
}
