module com.mpas.cems.repos {
    requires com.mpas.cems.dao;
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.tx;  // needed only for testing @Transactional on repo
    requires java.sql;
    requires org.apache.commons.lang3;

    exports com.mpas.cems.repos;
    exports com.mpas.cems.repos.util;
    exports com.mpas.cems.repos.impl;
}