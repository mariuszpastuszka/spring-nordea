
package com.mpas.cems.tx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.repos", "com.mpas.cems.tx.services"})
// TODO 31. Enable declarative transaction
public class AppConfig {
}
