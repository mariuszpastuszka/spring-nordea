package com.mpas.cems.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersonRepoMonitor {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepoMonitor.class);

    //@Before("execution(public * com.mpas.cems.repos.*.JdbcPersonRepo+.findById(..))")
    public void beforeFindById(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + methodName + " is about to be called");
    }
}
