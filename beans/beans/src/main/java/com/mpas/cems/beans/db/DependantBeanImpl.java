package com.mpas.cems.beans.db;

import com.mpas.cems.beans.ci.SimpleBean;


public class DependantBeanImpl implements DependantBean {
    private SimpleBean simpleBean;

    public DependantBeanImpl(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }
}
