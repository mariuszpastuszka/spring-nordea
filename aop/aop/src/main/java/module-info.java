module com.mpas.cems.aop {
    exports com.mpas.cems.aop.service;
    exports com.mpas.cems.aop.exception;
    requires com.mpas.cems.dao;
    requires com.mpas.cems.repos;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires spring.jdbc;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires org.aspectj.weaver;
    requires org.slf4j;
}