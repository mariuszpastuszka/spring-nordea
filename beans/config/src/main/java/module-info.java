module com.mpas.cems.config {
    requires com.mpas.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;
    requires org.slf4j;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jdbc;
    requires java.naming;
    requires java.annotation;

    requires com.mpas.cems.pojos;
    requires ojdbc8;
}