package com.mpas.cems.aop;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import com.mpas.cems.ex.UnexpectedException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Aspect
@Component
public class PersonMonitor {
    private static final Logger logger = LoggerFactory.getLogger(PersonMonitor.class);
    private static long findByIdCount = 0;

    //@Before("execution(public * com.mpas.cems.repos.*.JdbcPersonRepo+.findById(..))")
    @Before("execution(public * com.mpas.cems.repos.*.JdbcPersonRepo+.findById(..)) && within(com.mpas.*) ")
    public void beforeFindById(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        logger.info("[beforeFindById]: ---> Method {} is about to be called", methodName);
    }


    @Before("execution(@com.mpas.cems.repos.ApressRepo * com.mpas.cems.repos.*.*Repo+.*(..))")
    //@Before("execution(public * com.mpas.cems.repos.*.PersonRepo+.findById(..)) && @annotation(com.mpas.cems.repos.ApressRepo))")
    //@Before("@annotation(com.mpas.cems.repos.ApressRepo)")
    public void beforeFindByIdWithMethodAnnotation(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        logger.info("[beforeFindByIdWithMethodAnnotation]: ---> Method {} is about to be called", methodName);
    }

    //@Before("execution(* com.mpas.cems.*.*PersonRepo+.findBy*(..)) || execution (* com.mpas.cems.aop.service.*Service+.findBy*(..)))")
    @Before("com.mpas.cems.aop.PointcutContainer.repoFind() || com.mpas.cems.aop.PointcutContainer.serviceFind()")
    public void beforeFind(JoinPoint joinPoint) {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        logger.info("[beforeFind]: ---> Method {}.{}  is about to be called", className, methodName);
    }

    @After("com.mpas.cems.aop.PointcutContainer.repoFind() || com.mpas.cems.aop.PointcutContainer.serviceFind()")
    public void afterFind(JoinPoint joinPoint) {
        ++findByIdCount;
        var methodName = joinPoint.getSignature().getName();
        logger.info("[afterFind]: ---> Method {}  was called {}  times", methodName, findByIdCount);
    }

    private static final String[] SPECIAL_CHARS = new String[]{"$", "#", "&", "%"};

    @Before("com.mpas.cems.aop.PointcutContainer.beforeSavePointcut(person,service)")
    public void beforeSave(Person person, PersonService service) {
        logger.info("[beforeSave]: ---> Target object {}", service.getClass());
        person.setFirstName("Sample");
        if (StringUtils.indexOfAny(person.getFirstName(), SPECIAL_CHARS) != -1 ||
                StringUtils.indexOfAny(person.getLastName(), SPECIAL_CHARS) != -1) {
            throw new IllegalArgumentException("Text contains weird characters!");
        }
    }

    @AfterReturning(value = "execution (* com.mpas.cems.aop.service.*Service+.save(..))", returning = "result")
    public void afterServiceSave(JoinPoint joinPoint, Person result) {
        logger.info("[afterServiceSave]: ---> Target object {}", joinPoint.getTarget().getClass());
        logger.info("[afterServiceSave]: ---> Was person saved? {}", (result != null));
    }

    @AfterThrowing(value = "execution(public * com.mpas.cems.repos.*.JdbcPersonRepo+.update(..))", throwing = "e")
    public void afterBadUpdate(JoinPoint joinPoint, Exception e) {
        var className = joinPoint.getSignature().getDeclaringTypeName();
        var methodName = joinPoint.getSignature().getName();
        if (e instanceof IllegalArgumentException) {
            logger.info("[afterBadUpdate]: ---> Update method {}.{} failed because of bad data.", className, methodName);
        } else {
            throw new UnexpectedException(" Ooops!", e);
        }
    }

    @Around("com.mpas.cems.aop.PointcutContainer.repoFind() || com.mpas.cems.aop.PointcutContainer.serviceFind()")
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
