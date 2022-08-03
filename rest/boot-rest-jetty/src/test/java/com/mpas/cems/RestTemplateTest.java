
package com.mpas.cems;

import com.mpas.cems.person.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestApplication.class)
class RestTemplateTest {

    @LocalServerPort
    private Integer port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });
    }

    @BeforeEach
    void setUp(){
        baseUrl = baseUrl.concat(":").concat(port.toString()).concat("/persons");
    }

    @Test
    void shouldReturnAListOfPersons(){
        Person[] persons = restTemplate.getForObject(baseUrl, Person[].class);
        assertAll(
                () -> assertNotNull(persons),
                () -> assertTrue(persons.length >= 4)
        );
    }

    @Test
    void shouldReturnAPerson(){
        Person person = restTemplate.getForObject(baseUrl.concat("/{id}"), Person.class, 1);
        assertAll(
                () -> assertNotNull(person),
                () -> assertEquals("sherlock.holmes", person.getUsername())
        );
    }

    @Test
    void shouldReturnAPersonWithCallback(){
        String url = baseUrl + "/{id}"; // http://localhost:8081/persons/1
        Person person  = restTemplate.execute(url, HttpMethod.GET,
                request -> {
                    HttpHeaders headers = request.getHeaders();
                    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
                    System.out.println("Request headers = " + headers);
                }, new HttpMessageConverterExtractor<>(Person.class,
                        restTemplate.getMessageConverters())
                , new HashMap<String, Long>() {{
                    put("id", 1L);
                }});

        assertAll(
                () -> assertNotNull(person),
                () -> assertEquals("sherlock.holmes", person.getUsername())
        );
    }


    @Test
    void shouldCreateAPerson() {
        Person person = buildPerson("gigi.pedala", "Gigi", "Pedala", "1dooh2" );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> postRequest = new HttpEntity<>(person, headers);
        Person newPerson = restTemplate.postForObject(baseUrl, postRequest, Person.class);

        assertAll(
                () -> assertNotNull(newPerson),
                () -> assertEquals(person.getUsername(), newPerson.getUsername()),
                () -> assertNotNull(newPerson.getId())
        );
    }

    @Test
    void shouldCreateAPersonUsingExchange() {
        Person person = buildPerson("titi.pedala", "Titi", "Pedala", "1dooh3" );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> postRequest = new HttpEntity<>(person, headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, postRequest, Person.class);

        Person newPerson = responseEntity.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertNotNull(newPerson),
                () -> assertEquals(person.getUsername(), newPerson.getUsername()),
                () -> assertNotNull(newPerson.getId())
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldNotCreateAPerson() {
        Person person = buildPerson("gigi.pedala", "Gi", "Pe", "1dooh2" );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> postRequest = new HttpEntity<>(person, headers);
        ResponseEntity<String> err = restTemplate.exchange(baseUrl.concat("2"), HttpMethod.POST, postRequest,  String.class);
        assertAll(
                () -> assertNotNull(err),
                () -> assertEquals(HttpStatus.BAD_REQUEST, err.getStatusCode()),
                () -> assertTrue(err.getBody().contains("firstName")),
                () -> assertTrue(err.getBody().contains("lastName"))
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
