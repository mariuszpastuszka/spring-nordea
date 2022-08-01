
package com.mpas.cems.dj;

import com.mpas.cems.ex.ConfigurationException;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


//@Configuration
public class OracleDataSourceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", connectionProperties().getProperty("dialect"));
        hibernateProp.put("hibernate.hbm2ddl.auto", connectionProperties().getProperty("hbm2ddl"));

        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        return hibernateProp;
    }

    @Bean("connectionProperties")
    Properties connectionProperties(){
        try {
            return PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("prod-database.properties"));
        } catch (IOException e) {
            throw new ConfigurationException("Could not retrieve connection properties!", e);
        }
    }

    @Bean
    public DataSource dataSource() {
        try {
            final Properties props = connectionProperties();
            OracleConnectionPoolDataSource ods = new OracleConnectionPoolDataSource();
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
