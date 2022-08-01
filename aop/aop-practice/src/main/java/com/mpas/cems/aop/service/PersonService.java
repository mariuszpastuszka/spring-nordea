package com.mpas.cems.aop.service;

import com.mpas.cems.dao.Person;

import java.util.Optional;
import java.util.Set;


public interface PersonService {
    Set<Person> findAll();

    long count();

    Optional<Person> findById(Long id);

    Person save(Person person);

    Person updateFirstName(Person person, String newFirstname);

    void delete(Person person);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByCompleteName(String firstName, String lastName);


}
