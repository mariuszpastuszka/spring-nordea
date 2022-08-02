
package com.mpas.cems.practice.boot;

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
    void testList() throws Exception {
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
    void testShow() throws Exception {
        // TODO 48. Write a test to check that checks that requesting "/persons/1" generates the appropriate response
    }

   @Test
    void testError() throws Exception {
       // TODO 49. Write a test to check that checks that requesting "/persons/99" generates the appropriate response
    }
}
