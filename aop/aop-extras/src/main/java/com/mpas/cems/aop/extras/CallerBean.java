package com.mpas.cems.aop.extras;

import org.springframework.stereotype.Component;


@Component
public class CallerBean {

    private TargetBean targetBean;

    public CallerBean(TargetBean targetBean) {
        this.targetBean = targetBean;
    }

    public void doStuff() {
        targetBean.publicMethod();
        targetBean.protectedMethod();
        targetBean.packageMethod();

        targetBean.proxyMethod();
    }
}
