package com.mpas.cems.aop.within;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PointcutContainer {

    // all methods in classes in package com.mpas.cems.repos
    // @Pointcut("within(com.mpas.cems.repos..*)")

    // all methods in classes implementing PersonRepo
    @Pointcut("within(com.mpas.cems.repos.PersonRepo+)")
    public void onAnyRepoMethod() {
    }

    @Pointcut("within( com.mpas.cems.aop.service.*)")
    public void onAnyServiceMethod() {
    }

}
