
package com.mpas.cems.person;

import com.mpas.cems.ex.NotFoundException;
import com.mpas.cems.person.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


//@Controller
//@RequestMapping("/persons/{id}")
public class SinglePersonController2 {

    private PersonService personService;

    public SinglePersonController2(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Finds the person managed by the methods in this controller and adds it to the model
     * @param id
     *      the id of the Person instance to retrieve
     * @return person
     */
    @ModelAttribute
    protected Person modelPerson(@PathVariable Long id){
        Optional<Person> personOpt = personService.findById(id);
        if(personOpt.isPresent()) {
            return personOpt.get();
        } else {
            throw new NotFoundException(Person.class, id);
        }
    }

    /**
     * Handles requests to show detail about one person.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String show() {
        return "persons/show";
    }

    @GetMapping("/edit")
    public String edit() {
        return "persons/edit";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String update(@Validated Person person, BindingResult result){
        // do some logic
        return "redirect:/persons/" + person.getId();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Validated Person person, BindingResult result){
        //do some logic
        return "redirect:/persons/" + person.getId();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(){
        //do some logic
        return "redirect:/persons/list";
    }
}
