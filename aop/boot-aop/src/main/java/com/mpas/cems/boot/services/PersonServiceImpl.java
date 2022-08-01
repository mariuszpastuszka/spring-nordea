package com.mpas.cems.boot.services;

import com.mpas.cems.boot.entities.Person;
import com.mpas.cems.boot.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private PersonRepo personRepo;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Person> findAll() {
        Set<Person> persons = new HashSet<>();
        personRepo.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public long count() {
        return personRepo.count();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepo.findById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }
}
