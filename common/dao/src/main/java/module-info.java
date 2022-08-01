module com.mpas.cems.dao {
    requires java.persistence;
    requires spring.context;
    requires java.validation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports com.mpas.cems.dao;
    exports com.mpas.cems.dto;
    exports com.mpas.cems.util;
    exports com.mpas.cems.ex;
}