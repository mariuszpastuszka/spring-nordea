package com.mpas.cems.aop.within;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.aop.service", "com.mpas.cems.aop.within", "com.mpas.cems.repos"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WithinConfig {
}
