package com.mpas.cems.aop;

import com.mpas.cems.dao.Person;
import com.mpas.cems.ex.UnexpectedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


// TODO 20. Declare this class as an aspect
public class PersonMonitor {
    private static final Logger logger = LoggerFactory.getLogger(PersonMonitor.class);
    private static long findByIdCount = 0;

    /* TODO 21. Declare this method as an Before advice and use as pointcut expression the expression
     associated with the "repoFind" from the "PointcutContainer" class */
    public void beforeFind(JoinPoint joinPoint) {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        logger.info("[beforeFind]: ---> Method {}.{}  is about to be called", className, methodName);
    }

    /* TODO 22. Declare this method as an After advice and use as pointcut expression a composed expression
     made from the "serviceFind" and "repoFind" from the "PointcutContainer" class */
    public void afterFind(JoinPoint joinPoint) {
        ++findByIdCount;
        var methodName = joinPoint.getSignature().getName();
        logger.info("[afterFind]: ---> Method {}  was called {}  times", methodName, findByIdCount);
    }

    /* TODO 23. Declare this method as an AfterReturning advice and create a pointcut expression that matches any method
    with the name starting with "save" that is defined in a class with the name containing "Service" */
    public void afterServiceSave(JoinPoint joinPoint, Person result) {
        logger.info("[afterServiceSave]: ---> Target object {}", joinPoint.getTarget().getClass());
        logger.info("[afterServiceSave]: ---> Was person saved? {}", (result != null));
    }

    /* TODO 24. Declare this method as an AfterThrowing advice and create a pointcut expression that matches any method
     with the name starting with "update" that is defined in a class with the name containing "Repo" */
    public void afterUpdate(JoinPoint joinPoint, Exception e) {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        if (e instanceof IllegalArgumentException) {
            logger.info("[afterUpdate]: ---> Update method {}.{} failed because of bad data.", className, methodName);
        } else {
            throw new UnexpectedException(" Ooops!", e);
        }
    }

    /* TODO 25. Declare this method as an Around advice and use as pointcut expression a composed expression
     made from the "serviceFind" and "repoFind" from the "PointcutContainer" class */
    public Object aroundFind(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        logger.info("[aroundFind]: ---> Intercepting call of {}", methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            var obj = joinPoint.proceed();
            return obj != null ? obj : Optional.empty();
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info("[aroundFind]: ---> Execution of {} took {} ", methodName, (t2 - t1) / 1000 + " seconds.");
        }
    }

}
