module com.mpas.cems.emf {
    requires com.mpas.cems.dao;
    requires com.mpas.cems.repos;
    requires com.mpas.cems.aop;

    requires org.hibernate.orm.core;

    requires org.apache.commons.lang3;
    requires java.sql;
    requires org.slf4j;
    requires java.naming;
    requires java.annotation;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;
    requires spring.tx;
    requires spring.orm;
    requires java.persistence;
}