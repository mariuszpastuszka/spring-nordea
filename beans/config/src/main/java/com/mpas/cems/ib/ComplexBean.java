package com.mpas.cems.ib;


import com.mpas.cems.lc.AnotherSimpleBean;
import com.mpas.cems.lc.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ComplexBean implements InitializingBean, DisposableBean {
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
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Stage 3: Calling the afterPropertiesSet.");
        var ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            anotherSimpleBean = new AnotherSimpleBean();
        }
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Stage 4: Calling the destroy method.");
    }
}
