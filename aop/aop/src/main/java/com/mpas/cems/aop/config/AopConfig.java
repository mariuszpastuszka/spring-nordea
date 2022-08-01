package com.mpas.cems.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.aop", "com.mpas.cems.repos"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
}
