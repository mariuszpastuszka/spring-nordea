module com.mpas.cems.aop.xml {
    requires com.mpas.cems.dao;
    requires com.mpas.cems.repos;
    requires org.apache.commons.lang3;
    requires java.sql;

    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires org.aspectj.weaver;
    requires org.slf4j;
}