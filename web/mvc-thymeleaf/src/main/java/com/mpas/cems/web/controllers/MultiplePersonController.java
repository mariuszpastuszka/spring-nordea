
package com.mpas.cems.web.controllers;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.problem.InvalidCriteriaException;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.dto.CriteriaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static com.mpas.cems.util.Functions.COMPARATOR_BY_ID;


@Controller
@RequestMapping("/persons")
public class MultiplePersonController {

    private Logger logger = LoggerFactory.getLogger(MultiplePersonController.class);

    private PersonService personService;
    private MessageSource messageSource;

    public MultiplePersonController(PersonService personService, MessageSource messageSource) {
        this.personService = personService;
        this.messageSource = messageSource;
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

    // --------------- search  -------------------
    @GetMapping(value = "/search")
    public String search(CriteriaDto criteria) {
        return "persons/search";
    }

    @GetMapping(value = "/go")
    public String processSubmit(@Validated @ModelAttribute("criteriaDto") CriteriaDto criteria, BindingResult result, Model model, Locale locale) {
        if (result.hasErrors()) {
            return "persons/search";
        }
        try {
            List<Person> persons = personService.getByCriteriaDto(criteria);
            if (persons.size() == 0) {
                result.addError(new FieldError("criteriaDto", "noResults", messageSource.getMessage("NotEmpty.criteriaDto.noResults", null, locale)));
                return "persons/search";
            } else if (persons.size() == 1) {
                return "redirect:/persons/" + persons.get(0).getId();
            } else {
                model.addAttribute("persons", persons);
                return "persons/list";
            }
        } catch (InvalidCriteriaException ice) {
            result.addError(new FieldError("criteriaDto", ice.getFieldName(),
                    messageSource.getMessage(ice.getMessageKey(), null, locale)));
            return "persons/search";
        }
    }
}
