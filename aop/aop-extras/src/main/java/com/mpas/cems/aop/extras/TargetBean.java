package com.mpas.cems.aop.extras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;


@Component
public class TargetBean {

    private Logger logger = LoggerFactory.getLogger(TargetBean.class);

    public void publicMethod() {
        logger.debug(" -----> executing  publicMethod");
    }

    protected void protectedMethod() {
        logger.debug(" -----> executing  protectedMethod");
    }

    protected void packageMethod() {
        logger.debug(" -----> executing  packageMethod");
    }

    void proxyMethod() {
        logger.debug(" -----> executing  proxyMethod");
        ((TargetBean) (AopContext.currentProxy())).internalMethod();
    }

    void internalMethod() {
        logger.debug(" -----> executing  internalMethod");
    }

}
