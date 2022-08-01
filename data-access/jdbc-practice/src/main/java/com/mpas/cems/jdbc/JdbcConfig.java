
package com.mpas.cems.jdbc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.jdbc.repos"})
public class JdbcConfig {
}
