

module com.mpas.cems.web.thymeleaf {
    requires com.mpas.cems.dao;
    requires com.mpas.cems.dj;

    requires java.naming;

    requires spring.core;
    requires spring.webmvc;
    requires spring.context;
    requires spring.web;

    requires javax.servlet.api;
    requires org.slf4j;
    requires java.annotation;
    requires com.zaxxer.hikari;
    requires spring.beans;
    requires java.sql;

    requires thymeleaf.spring5;
    requires thymeleaf;
    requires java.validation;

    exports com.mpas.cems.web.config;
    exports com.mpas.cems.web.controllers;
    exports com.mpas.cems.web.problem;
    opens com.mpas.cems.web.config to spring.core;
}