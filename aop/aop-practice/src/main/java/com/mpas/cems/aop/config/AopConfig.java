package com.mpas.cems.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.aop", "com.mpas.cems.repos"})
// TODO 19. Enable aspect support
public class AopConfig {
}
