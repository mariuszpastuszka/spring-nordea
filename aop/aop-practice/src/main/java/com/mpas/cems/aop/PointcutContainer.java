package com.mpas.cems.aop;

import org.aspectj.lang.annotation.Pointcut;


public class PointcutContainer {

    @Pointcut("execution(* com.mpas.cems.*.*PersonRepo+.findBy*(..))")
    public void repoFind() {
    }

    @Pointcut("execution (* com.mpas.cems.aop.service.*Service+.findBy*(..)))")
    public void serviceFind() {
    }

}
