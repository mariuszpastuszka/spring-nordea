
package com.mpas.cems.dj.sandbox.repos;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.dj.sandbox.repos"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.mpas.cems.dj.sandbox.repos"})
public class AppConfig {
}
