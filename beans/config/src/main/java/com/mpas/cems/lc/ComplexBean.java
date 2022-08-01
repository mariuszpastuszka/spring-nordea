package com.mpas.cems.lc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class ComplexBean {
    private Logger logger = LoggerFactory.getLogger(ComplexBean.class);

    private SimpleBean simpleBean;

    private AnotherSimpleBean anotherSimpleBean;

    public ComplexBean() {
        logger.info("Stage 1: Calling the constructor.");
    }

    @Autowired
    public void setSimpleBean(SimpleBean simpleBean) {
        logger.info("Stage 2: Calling the setter.");
        this.simpleBean = simpleBean;
    }

    /**
     * The initialization method.
     * Just for fun: it instantiates the anotherSimpleBean only
     * if the current time is even.
     */
    @PostConstruct
    private void initMethod() {
        logger.info("Stage 3: Calling the initMethod.");
        var ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            anotherSimpleBean = new AnotherSimpleBean();
        }
    }

    @PreDestroy
    private void destroy() {
        logger.info("Stage 4: Calling the destroy method.");
    }
}
