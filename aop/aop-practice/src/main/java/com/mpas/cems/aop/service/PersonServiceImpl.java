package com.mpas.cems.aop.service;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepo personRepo;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Set<Person> findAll() {
        return Set.copyOf(personRepo.findAll());
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
        personRepo.save(person);
        return person;
    }

    @Override
    public Person updateFirstName(Person person, String newFirstname) {
        person.setFirstName(newFirstname);
        return personRepo.update(person);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        return personRepo.findByCompleteName(firstName, lastName);
    }

    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }
}
