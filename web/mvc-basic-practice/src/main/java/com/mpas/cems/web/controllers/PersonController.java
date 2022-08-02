
package com.mpas.cems.web.controllers;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.web.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mpas.cems.util.Functions.COMPARATOR_BY_ID;


// TODO 45. Provide the proper configuration to transform this class into a Spring Controller that can handle requests to https://localhost:8080/mvc-basic-practice/persons/*
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Handles requests to list all persons.
     */
    // TODO 46. Provide the proper to handle GET requests to https://localhost:8080/mvc-basic-practice/persons/list
    public String list(Model model) {
        logger.info("Populating model with list...");
        List<Person> persons =  personService.findAll();
        persons.sort(COMPARATOR_BY_ID);
        model.addAttribute("persons", persons);
        return "persons/list";
    }

    /**
     * Handles requests to show detail about one person.
     */
    // TODO 47. Provide the proper to handle GET requests to https://localhost:8080/mvc-basic-practice/persons/show?id=[number]
    public String show(@RequestParam("id") Long id, Model model) throws NotFoundException {
        Optional<Person> personOpt = personService.findById(id);
       if(personOpt.isPresent()) {
           model.addAttribute("person", personOpt.get());
       } else {
           throw new NotFoundException(Person.class, id);
       }
        return "persons/show";
    }
}
