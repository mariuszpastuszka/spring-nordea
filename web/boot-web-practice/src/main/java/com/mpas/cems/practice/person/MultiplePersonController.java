
package com.mpas.cems.practice.person;

import com.mpas.cems.practice.ex.InvalidCriteriaException;
import com.mpas.cems.practice.person.services.PersonService;
import com.mpas.cems.practice.util.CriteriaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;



@Controller
@RequestMapping("/persons")
public class MultiplePersonController {
    static Comparator<Person> COMPARATOR_BY_ID = Comparator.comparing(Person::getId);

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
