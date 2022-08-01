package com.mpas.cems.aop.extras;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan
@EnableAspectJAutoProxy(exposeProxy = true)
public class AppConfig {
}
