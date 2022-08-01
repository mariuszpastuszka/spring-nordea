
package com.mpas.cems.dj.services;

import com.mpas.cems.dao.Person;

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

    //Person updatePassword(Person person, String password)throws MailSendingException;
}
