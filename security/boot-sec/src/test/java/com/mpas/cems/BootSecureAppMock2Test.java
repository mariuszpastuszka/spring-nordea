
package com.mpas.cems;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BootSecureAppMock2Test {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value="john")
    @Test
    void johnShouldHaveAccessToPersons() throws Exception {
        mockMvc.perform(get("/persons/list")).andExpect(status().isOk());
    }

    @WithMockUser(value="john")
    @Test
    void johnShouldHaveAccessToThisPerson() throws Exception {
        mockMvc.perform(get("/persons/1")).andExpect(status().isOk());
    }

    @WithMockUser(value="john")
    @Test
    void johnShouldBeAllowedToEditThisPerson() throws Exception {
        mockMvc.perform(get("/persons/1/edit")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(value="john")
    @Test
    void johnShouldGetAnError() throws Exception {
        mockMvc.perform(get("/persons/99"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("problem", not(emptyString())));
    }
}
