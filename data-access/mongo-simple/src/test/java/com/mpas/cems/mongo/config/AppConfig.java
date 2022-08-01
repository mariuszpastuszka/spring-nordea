
package com.mpas.cems.mongo.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "com.mpas.cems.mongo.repos")
@ComponentScan(basePackages = {"com.mpas.cems.mongo.services"})
@PropertySource("classpath:mongo.properties")
public class AppConfig {

    @Value("${db.name}")
    private String dbName;

    @Value("${db.host}")
    private String host;

    @Value("${db.port}")
    private Integer port;

    @Bean
    public MongoDbFactory mongoDb() {
        return new SimpleMongoDbFactory(new MongoClient(host, port), dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDb());
    }
}