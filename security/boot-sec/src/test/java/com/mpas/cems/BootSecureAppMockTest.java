
package com.mpas.cems;

import com.mpas.cems.person.Person;
import com.mpas.cems.person.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class BootSecureAppMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService mockService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    @WithMockUser(value="john")
    @Test
     void johnShouldHaveAccessToPersons() throws Exception {
        List<Person> list = new ArrayList<>();
        Person p = new Person();
        p.setId(1L);
        p.setFirstName("Sherlock");
        p.setLastName("Holmes");
        list.add(p);
        when(mockService.findAll()).thenReturn(list);

        mockMvc.perform(get("/persons/list")).andExpect(status().isOk());
    }

    @WithMockUser(value="john")
    @Test
    void johnShouldNotBeAllowedToEditPersons() throws Exception {
        Person p = new Person();
        p.setId(1L);
        p.setFirstName("Sherlock");
        p.setLastName("Holmes");
        when(mockService.findById(anyLong())).thenReturn(Optional.of(p));
        mockMvc.perform(get("/persons/1/edit")).andExpect(status().isForbidden());
    }

}
