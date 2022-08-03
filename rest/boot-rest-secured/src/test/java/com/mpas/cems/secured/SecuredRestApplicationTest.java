
package com.mpas.cems.secured;

import com.mpas.cems.secured.person.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredRestApplicationTest {
    @LocalServerPort
    private Integer port;

    private String baseUrl = "http://localhost";

    private static  TestRestTemplate testRestTemplate = null;

    @BeforeAll
    static void init() {
        testRestTemplate = new TestRestTemplate("jane", "doe");

        // or
        //testRestTemplate = new TestRestTemplate();
        //testRestTemplate.withBasicAuth("jane", "doe");
    }

    @BeforeEach
    void setUp(){
        baseUrl = baseUrl.concat(":").concat(port.toString()).concat("/persons");
    }

    @Test
    void shouldReturnAListOfPersons(){
        
        ResponseEntity<Person[]> response = testRestTemplate.
                getForEntity(baseUrl, Person[].class);

        Person[] persons = response.getBody();
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(persons),
                () -> assertTrue(persons.length >= 4)
        );
    }

    @Test
    void shouldUpdateAPerson() {
        Person person = buildPerson("sherlock.holmes", "Sherlock Cornelius", "Holmes", "complicated");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> putRequest = new HttpEntity<>(person, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(baseUrl.concat("/1"), HttpMethod.PUT, putRequest, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotUpdateAPerson403(){
        Person person = buildPerson("sherlock.holmes", "Sherlock Cornelius", "Holmes", "complicated");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> putRequest = new HttpEntity<>(person, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.withBasicAuth("john", "doe").exchange(baseUrl.concat("/1"), HttpMethod.PUT, putRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void shouldCreateAPerson() {
        Person person = buildPerson("gigi.pedala", "Gigi", "Pedala", "1dooh2" );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> postRequest = new HttpEntity<>(person, headers);
        URI uri = testRestTemplate.postForLocation(baseUrl, postRequest, Person.class);

        assertNotNull(uri);
        Person newPerson = testRestTemplate.getForObject(uri, Person.class);
        assertAll(
                () -> assertNotNull(newPerson),
                () -> assertEquals(person.getUsername(), newPerson.getUsername()),
                () -> assertNotNull(newPerson.getId())
        );
    }

    private Person buildPerson(final String username, final String firstName, final String lastName, final String password) {
        Person person = new Person();
        person.setUsername(username);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPassword(password);
        person.setHiringDate(LocalDateTime.now());
        return person;
    }
}
