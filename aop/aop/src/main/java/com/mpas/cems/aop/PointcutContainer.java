package com.mpas.cems.aop;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import org.aspectj.lang.annotation.Pointcut;


public class PointcutContainer {

    @Pointcut("execution(* com.mpas.cems.*.*PersonRepo+.findBy*(..))")
    public void repoFind() {
    }

    @Pointcut("execution (* com.mpas.cems.aop.service.*Service+.findBy*(..)))")
    public void serviceFind() {
    }

    @Pointcut("execution (* com.mpas.cems.aop.service.*Service+.save(..)) && args(person) && target(service)")
    public void beforeSavePointcut(Person person, PersonService service) {
    }

    @Pointcut("execution(* com.mpas.cems.aop.service.*Service+.save*(..))")
    public void proxyBubu() {
    }
}
