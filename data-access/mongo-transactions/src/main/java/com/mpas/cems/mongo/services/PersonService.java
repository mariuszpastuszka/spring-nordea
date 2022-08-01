
package com.mpas.cems.mongo.services;

import com.mpas.cems.mongo.dao.Person;

import java.util.List;


public interface PersonService {

    void save(Person person);

    List<Person> findAll();

    List<Person> findByLastName(String lastname);

    Person findByUsername(String username);

    void deleteAll();
}
