
package com.mpas.cems.dj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.dj.services"})
@EnableJpaRepositories(basePackages = {"com.mpas.cems.dj.repos"})
@EnableTransactionManagement
public class AppConfig {
}
