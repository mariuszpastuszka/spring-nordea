package com.mpas.cems.config;

import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.repos.JdbcDetectiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"com.mpas.cems.repos"})
public class RepositoryConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    DetectiveRepo detectiveRepo() {
        return new JdbcDetectiveRepo(dataSource);
    }
}
