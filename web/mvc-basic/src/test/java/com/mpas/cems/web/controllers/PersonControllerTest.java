
package com.mpas.cems.web.controllers;

import com.mpas.cems.dao.Person;
import com.mpas.cems.dj.services.PersonService;
import com.mpas.cems.web.problem.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock //Creates mock instance of the field it annotates
    private PersonService mockService;

    @InjectMocks
    private PersonController personController;

    @SuppressWarnings("unchecked")
    @Test
    void testListHandler() {
        List<Person> list = new ArrayList<>();
        Person p = new Person();
        p.setId(1L);
        list.add(p);

        when(mockService.findAll()).thenReturn(list);

        ExtendedModelMap model = new ExtendedModelMap();
        String viewName = personController.list(model);
        List<Person> persons = (List<Person>) model.get("persons");

        assertAll(
                () -> assertNotNull(persons),
                () -> assertEquals(1, persons.size()),
                () -> assertEquals("persons/list", viewName)
        );
    }

    @Test
    void testShowHandler() throws NotFoundException {
        Person p = new Person();
        p.setId(1L);

        when(mockService.findById(any(Long.class))).thenReturn(Optional.of(p));

        ExtendedModelMap model = new ExtendedModelMap();
        String viewName = personController.show(1L, model);
        Person person = (Person) model.get("person");

        assertAll(
                () -> assertNotNull(person),
                () -> assertEquals(1L, person.getId()),
                () -> assertEquals("persons/show", viewName)
        );
    }
}