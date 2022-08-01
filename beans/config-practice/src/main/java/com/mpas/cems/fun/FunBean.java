package com.mpas.cems.fun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @since 1.0
 * Bean that is initialized using all three techniques supported in Spring
 */
// TODO 12. Add initialization and destroy methods to implement all three techniques specified in the book
public class FunBean /*implements InitializingBean, DisposableBean*/ {
    private Logger logger = LoggerFactory.getLogger(FunBean.class);

    private DepBean depBean;

    public FunBean() {
        logger.info("Stage 1: Calling the constructor");
    }

    @Autowired
    public void setDepBean(DepBean depBean) {
        logger.info("Stage 2: Calling the setter");
        this.depBean = depBean;
    }

    // ..
}
