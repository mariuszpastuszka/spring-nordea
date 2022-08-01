
package com.mpas.cems.tx.services;

import com.mpas.cems.aop.exception.MailSendingException;
import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
// TODO 32. Make all methods required to be executed in a read only transaction.
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
    public long countPersons() {
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
        return personRepo.update(person);
    }

    /*
     * TODO 33. Make this method execute in a read-write transaction and declare the
     *  transaction to rollback in case a MailSendingException exception is used
     */
    @Override
    public Person updatePassword(Person person, String password) throws MailSendingException {
        person.setPassword(password);
        Person p =  personRepo.update(person);
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
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        return personRepo.findByCompleteName(firstName,lastName);
    }

    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }
}
