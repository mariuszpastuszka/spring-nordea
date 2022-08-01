package com.mpas.cems.beans.si;

import com.mpas.cems.beans.ci.SimpleBean;


public interface AnotherComposedBean {

    SimpleBean getSimpleBean();

    Boolean isComplex();
}
