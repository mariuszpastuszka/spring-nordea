package com.mpas.cems.repos;

import com.mpas.cems.dao.Person;
import com.mpas.cems.pojos.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Set;


@Repository
public class JdbcPersonRepo extends JdbcAbstractRepo<Person> implements PersonRepo {

    public JdbcPersonRepo() {
    }

    @Autowired
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
