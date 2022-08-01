
package com.mpas.cems.boot.services;

import com.mpas.cems.boot.dao.Person;

import java.util.Optional;
import java.util.Set;


public interface PersonService {
    Set findAll();

    long count();

    Optional<Person> findById(Long id);

    Person save(Person person);

    void delete(Person person);

    Optional<Person> findByCompleteName(String firstName, String lastName);
}
