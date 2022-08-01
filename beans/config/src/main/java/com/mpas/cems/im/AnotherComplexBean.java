package com.mpas.cems.im;

import com.mpas.cems.lc.AnotherSimpleBean;
import com.mpas.cems.lc.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class AnotherComplexBean {
    private Logger logger = LoggerFactory.getLogger(AnotherComplexBean.class);

    private SimpleBean simpleBean;

    private AnotherSimpleBean anotherSimpleBean;

    public AnotherComplexBean() {
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
    void beanInitMethod() {
        logger.info("Stage 3: Calling the beanInitMethod.");
        var ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            anotherSimpleBean = new AnotherSimpleBean();
        }
    }

    void beanDestroyMethod() {
        logger.info("Stage 4: Calling the beanDestroyMethod.");
    }
}
