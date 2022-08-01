package com.mpas.cems.beans.si;

import com.mpas.cems.beans.ci.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class AnotherSimpleBeanImpl implements SimpleBean {
    private Logger logger = LoggerFactory.getLogger(AnotherSimpleBeanImpl.class);

    public AnotherSimpleBeanImpl() {
        logger.debug(" >> AnotherSimpleBeanImpl constructor is called.");
    }
}
