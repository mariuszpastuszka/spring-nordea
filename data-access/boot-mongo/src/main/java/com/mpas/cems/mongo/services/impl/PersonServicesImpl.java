
package com.mpas.cems.mongo.services.impl;

import com.mpas.cems.mongo.dao.Person;
import com.mpas.cems.mongo.repos.PersonRepo;
import com.mpas.cems.mongo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServicesImpl implements PersonService {

    private PersonRepo personRepo;

    @Autowired
    public PersonServicesImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public void save(Person person) {
        personRepo.save(person);
    }

    @Override
    public List<Person> findByLastName(String lastname) {
        return personRepo.findByLastName(lastname);
    }

    @Override
    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    public void deleteAll() {
        personRepo.deleteAll();
    }
}
