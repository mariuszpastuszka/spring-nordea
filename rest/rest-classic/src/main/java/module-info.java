

module com.mpas.cems.classic.rest {
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
    requires com.fasterxml.jackson.databind;

    exports com.mpas.cems.rest.config;
    exports com.mpas.cems.rest.controllers;
    exports com.mpas.cems.rest.problem;
    opens com.mpas.cems.rest.config to spring.core;
}