
module com.mpas.cems.dj {
    requires com.mpas.cems.dao;
    requires org.hibernate.orm.core;

    requires org.apache.commons.lang3;
    requires java.sql;
    requires org.slf4j;
    requires java.naming;
    requires java.annotation;

    requires spring.data.jpa;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;
    requires spring.tx;
    requires spring.orm;
    requires java.persistence;
    requires spring.data.commons;

    exports com.mpas.cems.dj;
    exports com.mpas.cems.dj.repos;
    exports com.mpas.cems.dj.problem;
    exports com.mpas.cems.dj.services;
    exports com.mpas.cems.dj.services.wrappers;
}