package com.mpas.cems.beans.ci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ComposedBeanImpl implements ComposedBean {

    private SimpleBean simpleBean;
    private String code;
    private Boolean complicated;

    // De-comment the @Qualifier annotation  to get rid of the exception
    @Autowired
    public ComposedBeanImpl(/*@Qualifier("anotherSimpleBean")*/ SimpleBean simpleBean, @Value("AB123") String code, @Value("true") Boolean complicated) {
        this.simpleBean = simpleBean;
        this.code = code;
        this.complicated = complicated;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public String getCode() {
        return code;
    }

    public Boolean isComplicated() {
        return complicated;
    }
}
