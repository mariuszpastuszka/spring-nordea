module com.mpas.cems.pojos {
    requires com.mpas.cems.dao;
    requires org.apache.commons.lang3;
    requires java.sql;

    exports com.mpas.cems.pojos.repos;
    exports com.mpas.cems.pojos.services;
    exports com.mpas.cems.pojos.services.impl;
}