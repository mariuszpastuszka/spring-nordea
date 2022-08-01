package com.mpas.cems.beans.fi;

import com.mpas.cems.beans.ci.ComposedBean;
import com.mpas.cems.beans.ci.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BadComposedBean implements ComposedBean {

    @Autowired
    private SimpleBean simpleBean;

    private String code;
    private Boolean complicated;

    public BadComposedBean(@Value("AB123") String code, @Value("true") Boolean complicated) {
        this.code = code;
        this.complicated = complicated;
    }

    @Override
    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Boolean isComplicated() {
        return complicated;
    }
}
