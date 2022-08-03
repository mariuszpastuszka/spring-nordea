
package com.mpas.cems.practice;

import com.mpas.cems.practice.person.Person;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PracticeRestApplication.class)
class PersonsControllerTest {
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

    @Order(1)
    @Test
    void shouldReturnAListOfPersons(){
        Person[] persons = restTemplate.getForObject(baseUrl, Person[].class);
        assertAll(
                () -> assertNotNull(persons),
                () -> assertTrue(persons.length == 4)
        );
    }

    @Order(2)
    @Test
    void shouldReturnAPerson(){
        //TODO. 61 Use the proper RestTemplate method to retrieve the person with id = 1
        Person person = null;
        assertAll(
                () -> assertNotNull(person),
                () -> assertEquals("sherlock.holmes", person.getUsername())
        );
    }

    @Order(3)
    @Test
    void shouldReturn404(){
        ResponseEntity<String> err = restTemplate.getForEntity(baseUrl + "/99", String.class);
        assertAll(
                () -> assertNotNull(err),
                () -> assertEquals(HttpStatus.NOT_FOUND, err.getStatusCode()),
                () -> assertTrue(err.getBody().contains("Unable to find entry with id 99"))
        );
    }

    @Order(4)
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

    @Order(5)
    @Test
    void shouldUpdateAPerson() {
        Person person = buildPerson("sherlock.holmes", "Sherlock Cornelius", "Holmes", "complicated");

        //TODO. 60 Use the proper RestTemplate method to update the person provided as request body
        ResponseEntity<Person> responseEntity = null;

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Order(6)
    @Test
    void shouldCreateAPerson() {
        Person person = buildPerson("gigi.pedala", "Gigi", "Pedala", "1dooh2" );

        //TODO 58. Use the proper RestTemplate method to save the person resource created previously
        URI uri = null;

        assertNotNull(uri);
        Person newPerson = restTemplate.getForObject(uri, Person.class);
        assertAll(
                () -> assertNotNull(newPerson),
                () -> assertEquals(person.getUsername(), newPerson.getUsername()),
                () -> assertNotNull(newPerson.getId())
        );
    }

    @Order(8)
    @SuppressWarnings("unchecked")
    @Test
    void shouldNotCreateAPerson() {
        Person person = buildPerson("titi.pedala", "ti", "Pe", "1dooh2" );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Person> postRequest = new HttpEntity<>(person, headers);
        ResponseEntity<String> err = restTemplate.exchange(baseUrl, HttpMethod.POST, postRequest,  String.class);
        assertAll(
                () -> assertNotNull(err),
                () -> assertEquals(HttpStatus.BAD_REQUEST, err.getStatusCode()),
                () -> assertTrue(err.getBody().contains("firstName")),
                () -> assertTrue(err.getBody().contains("lastName"))
        );
    }

    @Order(9)
    @Test
    void shouldDeleteAPerson(){
        //TODO. 62 Use the proper RestTemplate method to delete the person with id = 1

        ResponseEntity<String> err = restTemplate.getForEntity(baseUrl + "/1", String.class);
        assertAll(
                () -> assertNotNull(err),
                () -> assertEquals(HttpStatus.NOT_FOUND, err.getStatusCode()),
                () -> assertTrue(err.getBody().contains("Unable to find entry with id 1"))
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
