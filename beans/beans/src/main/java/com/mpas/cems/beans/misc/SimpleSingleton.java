package com.mpas.cems.beans.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleSingleton {
    private Logger logger = LoggerFactory.getLogger(SimpleSingleton.class);

    private static SimpleSingleton instance = new SimpleSingleton();

    private SimpleSingleton() {
        logger.info(">> Creating single instance at: " + System.currentTimeMillis());
    }

    public static synchronized SimpleSingleton getInstance() {
        return instance;
    }
}
