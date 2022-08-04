

module com.mpas.cems.sec.thymeleaf {
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
    requires spring.security.web;
    requires spring.security.config;
    requires spring.security.core;
    requires thymeleaf.extras.springsecurity5;

    exports com.mpas.cems.sec.config;
    exports com.mpas.cems.sec.controllers;
    exports com.mpas.cems.sec.problem;
    opens com.mpas.cems.sec.config to spring.core;
}