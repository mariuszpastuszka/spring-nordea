package com.mpas.cems.config;

import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.repos.JdbcDetectiveRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


// TODO 10. Split this configuration class in more configuration classes
@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.repos"})
@PropertySource("classpath:db/datasource.properties")
public class FullConfig {
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    DetectiveRepo detectiveRepo() {
        return new JdbcDetectiveRepo(dataSource());
    }
}
