
package com.mpas.cems.sec.controllers;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.sec.problem.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

import static com.mpas.cems.util.Functions.COMPARATOR_BY_ID;


@Controller
@RequestMapping("/persons")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Handles requests to list all persons.
     */
    @GetMapping(value = "/list")
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
    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) throws NotFoundException {
        Optional<Person> personOpt = personService.findById(id);
       if(personOpt.isPresent()) {
           model.addAttribute("person", personOpt.get());
       } else {
           throw new NotFoundException(Person.class, id);
       }
        return "persons/show";
    }

    @GetMapping(value = "/newPerson")
    public String newPerson(Person person, Model model){
        model.addAttribute("person", new Person());
        return "persons/newPerson";
    }
}
