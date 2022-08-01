package com.mpas.cems.fun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @since 1.0
 * Bean that is initialized using all three techniques supported in Spring
 */
public class FunBean implements InitializingBean, DisposableBean {
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

    @PostConstruct
    void initMethod() {
        logger.info("Stage 3: Calling the initMethod.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Stage 4: Calling the afterPropertiesSet.");
    }

    void beanInitMethod() {
        logger.info("Stage 5: Calling the beanInitMethod.");
    }


    @PreDestroy
    void destroyMethod() {
        logger.info("Stage 6: Calling the initMethod.");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Stage 7: Calling the initMethod.");
    }

    void beanDestroyMethod() {
        logger.info("Stage 8: Calling the beanInitMethod.");
    }

}
