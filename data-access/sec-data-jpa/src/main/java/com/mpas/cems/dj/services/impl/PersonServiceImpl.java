
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.problem.InvalidCriteriaException;
import com.mpas.cems.dj.repos.PersonRepo;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.dto.CriteriaDto;
import com.mpas.cems.dto.FieldGroup;
import com.mpas.cems.util.DateProcessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private PersonRepo personRepo;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> findAll() {
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


    @Override
    public Person save(Person person) {
        personRepo.save(person);
        return person;
    }

    @Override
    public Person updateFirstName(Person person, String newFirstname) {
        return personRepo.save(person);
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
        return personRepo.findByCompleteName(firstName,lastName);
    }

    @Override
    public void delete(Person person) {
        personRepo.delete(person);
    }

    @Override
    public List<Person> getByCriteriaDto(CriteriaDto criteria) throws InvalidCriteriaException {
        List<Person> persons = new ArrayList<>();
        FieldGroup fg = FieldGroup.getField(criteria.getFieldName());

        switch (fg) {
            case FIRSTNAME:
                persons = criteria.getExactMatch() ? personRepo.findByFirstName(criteria.getFieldValue())
                        : personRepo.findByFirstNameLike(criteria.getFieldValue());
                break;
            case LASTNAME:
                persons = criteria.getExactMatch() ? personRepo.findByLastName(criteria.getFieldValue())
                        : personRepo.findByLastNameLike(criteria.getFieldValue());
                break;
            case USERNAME:
                if(criteria.getExactMatch()) {
                    Optional<Person> persOpt = personRepo.findByUsername(criteria.getFieldValue());
                    if(persOpt.isPresent()) {
                        persons.add(persOpt.get());
                    }
                } else {
                    persons = personRepo.findByUsernameLike(criteria.getFieldValue());
                }
                break;
            case HIREDIN:
                LocalDateTime date;
                try {
                    date = DateProcessor.toDate(criteria.getFieldValue());
                } catch (DateTimeParseException e) {
                    throw new InvalidCriteriaException("fieldValue", "typeMismatch.hiringDate");
                }
                persons = personRepo.findByHiringDate(date);
                break;
        }
        return persons;
    }
}

