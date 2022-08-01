package com.mpas.cems.aop.cfg.classic;

import com.mpas.cems.aop.classic.NativePersonRepo;
import com.mpas.cems.repos.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class PersonNativeTest {

    @Autowired
    PersonRepo personRepo;

    @Test
    void testFindAll() {
        var persons = personRepo.findAll();

        assertAll(
                () -> assertNotNull(persons),
                () -> assertEquals(2, persons.size())
        );
    }

    @Configuration
    static class TestCtxConfig {
        @Bean
        PersonRepo jdbcPersonRepo() {
            return new NativePersonRepo(dataSource());
        }

        @Bean
        public DataSource dataSource() {
            var builder = new EmbeddedDatabaseBuilder();
            var db = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)
                    .addScript("db/schema.sql")
                    .addScript("db/test-data.sql")
                    .build();
            return db;
        }
    }
}
