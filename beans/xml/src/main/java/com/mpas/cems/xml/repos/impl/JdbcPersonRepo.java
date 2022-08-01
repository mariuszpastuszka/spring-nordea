package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.Person;
import com.mpas.cems.pojos.repos.PersonRepo;

import javax.sql.DataSource;
import java.util.Set;


public class JdbcPersonRepo extends JdbcAbstractRepo<Person> implements PersonRepo {

    public JdbcPersonRepo() {
    }

    public JdbcPersonRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Person findByUsername(String username) {
        return null;
    }

    @Override
    public Set<Person> findByCompleteName(String firstName, String lastName) {
        return null;
    }
}
