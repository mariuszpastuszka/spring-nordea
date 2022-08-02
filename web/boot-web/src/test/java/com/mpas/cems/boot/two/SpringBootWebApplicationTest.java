
package com.mpas.cems.boot.two;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SpringBootWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListPersons() throws Exception {
        mockMvc.perform(get("/persons/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons/list"))
                .andExpect(model().attribute("persons", hasSize(4)))
                .andExpect(model().attribute("persons", hasItem(
                        anyOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Sherlock")),
                                hasProperty("lastName", is("Holmes"))
                        )
                )));

    }

   @Test
    void testShowPerson() throws Exception {
        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons/show"))
                .andExpect(model().attribute("person", hasProperty("id", is(1L))))
                .andExpect(model().attribute("person", hasProperty("firstName", is("Sherlock"))))
                .andExpect(model().attribute("person", hasProperty("lastName", is("Holmes"))));

    }

    @Test
    void testErrorPerson() throws Exception {
        mockMvc.perform(get("/persons/99"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("problem", not(emptyString())));
    }
}
