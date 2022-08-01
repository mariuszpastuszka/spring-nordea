package com.mpas.cems.beans.ci;


public interface ComposedBean {
    SimpleBean getSimpleBean();

    String getCode();

    Boolean isComplicated();
}
