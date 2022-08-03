
package com.mpas.cems.rest.sec.controllers;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.dto.CriteriaDto;
import com.mpas.cems.rest.sec.problem.IllegalOperation;
import com.mpas.cems.rest.sec.problem.NotFoundException;
import com.mpas.cems.util.NumberGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mpas.cems.util.Functions.COMPARATOR_BY_ID;



@RestController
@RequestMapping("/persons")
public class PersonsController {

    private PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Handles requests to list all persons.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> list() {
        List<Person> persons =  personService.findAll();
        persons.sort(COMPARATOR_BY_ID);
        return persons;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search")
    public List<Person> processSubmit(@Validated @RequestBody CriteriaDto criteria) {
        return personService.getByCriteriaDto(criteria);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@Validated(Person.BasicValidation.class) @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalOperation("Cannot save entry!");
        }
        // This a workaround for a Jackson bug, the field is actually not deserialized. This is the issue I've found already created on GitHub:
        // https://github.com/FasterXML/jackson-databind/issues/935#issuecomment-520070413.
        // It is closed, but the bug is still there in version 2.9.9. when I asked about it,
        // I was told to create a new issue, which I will, as soon as this book is published.
        if(StringUtils.isEmpty(person.getPassword())){
            person.setPassword(NumberGenerator.getPassword());
        }
       return personService.save(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Person show(@PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            return personOpt.get();
        } else {
            throw new NotFoundException(Person.class, id);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Person updatedPerson, @PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setUsername(updatedPerson.getUsername());
            person.setFirstName(updatedPerson.getFirstName());
            person.setLastName(updatedPerson.getLastName());
            personService.save(person);
        } else {
            throw new NotFoundException(Person.class, id);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        personOpt.ifPresent(value -> personService.delete(value));
    }
}
