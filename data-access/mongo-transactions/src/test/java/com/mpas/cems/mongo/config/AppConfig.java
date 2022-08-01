
package com.mpas.cems.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "com.mpas.cems.mongo.repos")
@ComponentScan(basePackages = {"com.mpas.cems.mongo.services"})
@PropertySource("classpath:mongo.properties")
public class AppConfig extends AbstractMongoConfiguration {

    @Value("${db.name}")
    private String dbName;

    @Value("${db.host}")
    private String host;

    @Value("${db.port}")
    private Integer port;

    @Bean
    PlatformTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017/?replicaSet=rs0"));
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

}