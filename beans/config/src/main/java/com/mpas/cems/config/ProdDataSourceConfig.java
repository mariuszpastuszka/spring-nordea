package com.mpas.cems.config;

import com.mpas.cems.ex.ConfigurationException;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@Profile("prod")
public class ProdDataSourceConfig {

    @Bean("connectionProperties")
    Properties connectionProperties() {
        try {
            return PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("db/prod-datasource.properties"));
        } catch (IOException e) {
            throw new ConfigurationException("Could not retrieve connection properties!", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        try {
            final Properties props = connectionProperties();
            var ods = new OracleConnectionPoolDataSource();
            ods.setNetworkProtocol("tcp");
            ods.setDriverType(props.getProperty("driverType"));
            ods.setServerName(props.getProperty("serverName"));
            ods.setDatabaseName(props.getProperty("serviceName"));
            ods.setPortNumber(Integer.parseInt(props.getProperty("port")));
            ods.setUser(props.getProperty("user"));
            ods.setPassword(props.getProperty("password"));
            return ods;
        } catch (SQLException e) {
            throw new ConfigurationException("Could not configure Oracle database!", e);
        }
    }
}
