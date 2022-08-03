
package com.mpas.cems.person;

import com.mpas.cems.ex.IllegalOperation;
import com.mpas.cems.ex.InvalidCriteriaException;
import com.mpas.cems.ex.NotFoundException;
import com.mpas.cems.person.services.PersonService;
import com.mpas.cems.util.CriteriaDto;
import com.mpas.cems.util.NumberGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/persons")
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
    public List<Person> list() {
        List<Person> persons =  personService.findAll();
        persons.sort(COMPARATOR_BY_ID);
        return persons;
    }

    /**
     * Handles requests to create a person.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@Validated(Person.BasicValidation.class) @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            String errString = createErrorString(result);
            throw new IllegalOperation("Cannot save entry because: "+ errString);
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

    private String createErrorString(BindingResult result) {
        StringBuilder sb =  new StringBuilder();
        result.getAllErrors().forEach(error -> {
            if(error instanceof FieldError) {
                FieldError err= (FieldError) error;
                sb.append("Field '").append(err.getField()).append("' value error: ").append(err.getDefaultMessage());
            }
        });
        return sb.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> processSubmit(@Validated @RequestBody CriteriaDto criteria) {
        return personService.getByCriteriaDto(criteria);
    }

    /**
     * Returns the {@code Person} instance with id {@code id}
     * @param id
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person show(@PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            return personOpt.get();
        } else {
            throw new NotFoundException(Person.class, id);
        }
    }

    /**
     * Updates the {@code Person} instance with id {@code id}
     * @param updatedPerson
     * @param id
     * @return
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Person updatedPerson, @PathVariable Long id) {
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setUsername(updatedPerson.getUsername());
            person.setFirstName(updatedPerson.getFirstName());
            person.setLastName(updatedPerson.getLastName());
        } else {
            throw new NotFoundException(Person.class, id);
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound() {
        // just return empty 404
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidCriteriaException.class})
    public void handleInvalidCriteria() {
        // just return empty 400
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalOperation.class})
    public void handleIllegalOperation() {
        // just return empty 400
    }
}