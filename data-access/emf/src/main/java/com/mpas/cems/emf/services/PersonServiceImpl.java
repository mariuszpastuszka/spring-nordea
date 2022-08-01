package com.mpas.cems.emf.services;

import com.mpas.cems.aop.exception.MailSendingException;
import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service("personServiceImpl")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PersonServiceImpl implements PersonService {
    private PersonRepo personRepo;

    public PersonServiceImpl(@Qualifier("jpaPersonRepo") PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Set<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public long countPersons() {
        return personRepo.count();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepo.findById(id);
    }

    @Transactional
    @Override
    public Person save(Person person) {
        personRepo.save(person);
        return person;
    }

    @Transactional
    @Override
    public Person updateFirstName(Person person, String newFirstname) {
        return personRepo.update(person);
    }

    @Transactional
    @Override
    public Person updatePassword(Person person, String password) throws MailSendingException {
        person.setPassword(password);
        Person p = personRepo.update(person);
        sendNotification();
        return p;
    }

    private void sendNotification() throws MailSendingException {
        if (true) {
            throw new MailSendingException("Confirmation email for password could not be sent. Password was not updated.");
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    @Override
    public String getPersonAsHtml(String username) {
        final StringBuilder sb = new StringBuilder();
        personRepo.findByUsername(username).ifPresentOrElse(
                p -> sb.append("<p>First Name: ").append(p.getFirstName()).append(" </p>")
                        .append("<p>Last Name: ").append(p.getLastName()).append(" </p>"),
                () -> sb.append("<p>None found with username ").append(username).append(" </p>")
        );
        return sb.toString();
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        return personRepo.findByCompleteName(firstName, lastName);
    }

    @Transactional
    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }
}
