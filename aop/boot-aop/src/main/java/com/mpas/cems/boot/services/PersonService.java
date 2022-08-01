package com.mpas.cems.boot.services;

import com.mpas.cems.boot.entities.Person;

import java.util.Optional;
import java.util.Set;


public interface PersonService {
    Set findAll();

    long count();

    Optional<Person> findById(Long id);

    Person save(Person person);

    void delete(Person person);
}
