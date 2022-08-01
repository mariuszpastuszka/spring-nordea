package com.mpas.cems.beans.si;

import com.mpas.cems.beans.ci.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AnotherComposedBeanImpl implements AnotherComposedBean {

    private SimpleBean simpleBean;

    private Boolean complex;

    @Autowired
    public AnotherComposedBeanImpl(@Value("true") Boolean complex) {
        this.complex = complex;
    }

    @Autowired(required = false)
    public void setSimpleBean(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public Boolean isComplex() {
        return complex;
    }
}
