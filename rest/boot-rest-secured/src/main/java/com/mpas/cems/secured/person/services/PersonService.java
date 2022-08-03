
package com.mpas.cems.secured.person.services;

import com.mpas.cems.secured.ex.InvalidCriteriaException;
import com.mpas.cems.secured.person.Person;
import com.mpas.cems.secured.util.CriteriaDto;

import java.util.List;
import java.util.Optional;


public interface PersonService {
    List<Person> findAll();

    long countPersons();

    Optional<Person> findById(Long id);

    Person save(Person person);

    Person updateFirstName(Person person, String newFirstname);

    void delete(Person person);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByCompleteName(String firstName, String lastName);

    String getPersonAsHtml(String username);

    List<Person> getByCriteriaDto(CriteriaDto criteria) throws InvalidCriteriaException;

}