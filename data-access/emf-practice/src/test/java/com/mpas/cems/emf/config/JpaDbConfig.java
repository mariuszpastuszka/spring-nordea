
package com.mpas.cems.emf.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.util.Properties;


@Configuration
@PropertySource({"classpath:db/db.properties"})
public class JpaDbConfig {

    // ---------- configure the db ------------
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.dialect}")
    private String dialect;
    @Value("${db.hbm2ddl}")
    private String hbm2ddl;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.hbm2ddl.auto", hbm2ddl);

        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        return hibernateProp;
    }

    @Bean
    public DataSource dataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(driverClassName);
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);

            hikariConfig.setMaximumPoolSize(5);
            hikariConfig.setConnectionTestQuery("SELECT 1");
            hikariConfig.setPoolName("cemsPool");
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            return null;
        }
    }

    // TODO 39. Declare and configure the entity manager factory and the transaction manager beans.

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    //needed because Hibernate does not drop the database as it should
    @PostConstruct
    void discardDatabase(){
        final String currentDir = System.getProperty("user.dir");
        int start = url.indexOf("./")+ 2;
        int end = url.indexOf(";", start);
        String dbName = url.substring(start, end);
        File one  = new File(currentDir.concat(File.separator).concat(dbName).concat(".mv.db"));
        one.deleteOnExit();
        File two  = new File(currentDir.concat(File.separator).concat(dbName).concat(".trace.db"));
        two.deleteOnExit();
    }

}
