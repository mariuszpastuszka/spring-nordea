package com.mpas.cems.beans.scalars;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AppConvertersCfgTest {

    private Logger logger = LoggerFactory.getLogger(AppConvertersCfgTest.class);

    @Test
    void testSimpleBeans() {
        var ctx = new AnnotationConfigApplicationContext(AppConvertersCfg.class);

        var pb = ctx.getBean(PersonBean.class);
        assertNotNull(pb);
        logger.debug(pb.toString());

        var mtb = ctx.getBean(MultipleTypesBean.class);
        logger.debug(mtb.toString());

        var emptyCollectionHolder = ctx.getBean(EmptyCollectionHolder.class);
        logger.debug(emptyCollectionHolder.toString());

        var collectionHolder = ctx.getBean(CollectionHolder.class);
        logger.debug(collectionHolder.toString());
        ctx.close();
    }
}
