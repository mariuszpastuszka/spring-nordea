
package com.mpas.cems.practice.person;

import com.mpas.cems.practice.ex.InvalidCriteriaException;
import com.mpas.cems.practice.person.services.PersonService;
import com.mpas.cems.practice.util.CriteriaDto;
import com.mpas.cems.practice.util.NumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


// TODO 56. Place proper annotation here, to make this class handle REST requests with the root URI of '/persons'
public class PersonsController {
    private PersonService personService;
    static Comparator<Person> COMPARATOR_BY_ID = Comparator.comparing(Person::getId);
    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Handles requests to list all persons.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Person> list(Model model) {
        List<Person> persons =  personService.findAll();
        persons.sort(COMPARATOR_BY_ID);
        model.addAttribute("persons", persons);
        return persons;
    }

    /**
     * Handles requests to create a person.
     */
    //TODO 57. Place proper annotations to handle a REST POST request
    public void create(@Validated(Person.BasicValidation.class) @RequestBody Person person, BindingResult result, @Value("#{request.requestURL}")
            StringBuffer originalUrl, HttpServletResponse response) {
        if (result.hasErrors()) {
            String errString = createErrorString(result);
            throw new PersonsException(HttpStatus.BAD_REQUEST, "Cannot save entry because: "+ errString);
        }
        // In the code snippet there is a comment mentioning a workaround for a Jackson bug. This is the issue I've found already created on GitHub
        // https://github.com/FasterXML/jackson-databind/issues/935#issuecomment-520070413.
        // It is closed, but the bug is still there in version 2.9.9. when I asked about it,
        // I was told to create a new issue, which I will, as soon as this book is published.
        if(StringUtils.isEmpty(person.getPassword())){
            person.setPassword(NumberGenerator.getPassword());
        }
        try {
            Person newPerson = personService.save(person);
            response.setHeader("Location", getLocationForUser(originalUrl, newPerson.getId()));
        } catch (Exception e) {
            throw  new PersonsException(HttpStatus.UNPROCESSABLE_ENTITY, e);
        }
    }

    /**
     * Determines URL of user resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    static String getLocationForUser(StringBuffer url, Object childIdentifier) {
        UriTemplate template = new UriTemplate(url.toString() + "/{id}");
        return template.expand(childIdentifier).toASCIIString();
    }

    private String createErrorString(BindingResult result) {
        StringBuilder sb =  new StringBuilder();
        result.getAllErrors().forEach(error -> {
            if(error instanceof FieldError) {
                FieldError err= (FieldError) error;
                sb.append("Field '").append(err.getField()).append("' value error: ").append(err.getDefaultMessage()).append("\n");
            }
        });
        return sb.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> processSubmit(@Validated @RequestBody CriteriaDto criteria) {
        try {
            return personService.getByCriteriaDto(criteria);
        } catch (InvalidCriteriaException ice) {
            throw  new PersonsException(HttpStatus.BAD_REQUEST, ice);
        }
    }

    /**
     * Returns the {@code Person} instance with id {@code id}
     * @param id
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Person show(@PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            return personOpt.get();
        } else {
            throw new PersonsException(HttpStatus.NOT_FOUND, "Unable to find entry with id " + id );
        }
    }

    /**
     * Updates the {@code Person} instance with id {@code id}
     * @param updatedPerson
     * @param id
     * @return
     */
    // TODO 59. Place proper annotations to handle a REST POST request
    public void update(@RequestBody Person updatedPerson, @PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setUsername(updatedPerson.getUsername());
            person.setFirstName(updatedPerson.getFirstName());
            person.setLastName(updatedPerson.getLastName());
            personService.save(person);
        } else {
            throw new PersonsException(HttpStatus.NOT_FOUND, "Unable to find entry with id " + id );
        }
    }

    /**
     * Delete the {@code Person} instance with id {@code id}
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        personOpt.ifPresent(value -> personService.delete(value));
    }
}