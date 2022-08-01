package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.Person;

import java.util.Set;


public interface PersonRepo extends AbstractRepo<Person> {
    Person findByUsername(String username);

    Set<Person> findByCompleteName(String firstName, String lastName);
}
