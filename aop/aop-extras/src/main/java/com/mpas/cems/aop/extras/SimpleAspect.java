package com.mpas.cems.aop.extras;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class SimpleAspect {
    private Logger logger = LoggerFactory.getLogger(SimpleAspect.class);

    @Before("execution(!public * com.mpas.cems.aop.extras.TargetBean.*(..))")
    public void myAdvice(JoinPoint joinPoint) {
        logger.info(" -> [Before] calling " + joinPoint.getSignature().getName() + " method in " + joinPoint.getSignature().getDeclaringType());
    }
}
