
package com.mpas.cems.person;

import com.mpas.cems.ex.InvalidCriteriaException;
import com.mpas.cems.person.services.PersonAudit;
import com.mpas.cems.person.services.PersonService;
import com.mpas.cems.util.CriteriaDto;
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

import javax.servlet.http.HttpServletRequest;
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
    private PersonAudit audit;

    public MultiplePersonController(PersonService personService, MessageSource messageSource, PersonAudit audit) {
        this.personService = personService;
        this.messageSource = messageSource;
        this.audit = audit;
    }

    /**
     * Handles requests to list all persons.
     */
    @GetMapping(value = "/list")
    public String list(Model model, HttpServletRequest req) {
        audit.recordAction(" ->> " + req.getRequestURI());
        logger.info("Populating model with list...");
        List<Person> persons =  personService.findAll();
        persons.sort(COMPARATOR_BY_ID);
        model.addAttribute("persons", persons);
        return "persons/list";
    }

    // --------------- search  -------------------
    @GetMapping(value = "/search")
    public String search(CriteriaDto criteria, HttpServletRequest req) {
        audit.recordAction(" ->> " + req.getRequestURI());
        return "persons/search";
    }

    @GetMapping(value = "/go")
    public String processSubmit(@Validated @ModelAttribute("criteriaDto") CriteriaDto criteria, BindingResult result, Model model, Locale locale, HttpServletRequest req) {
        audit.recordAction(" ->> " + req.getRequestURI());
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
