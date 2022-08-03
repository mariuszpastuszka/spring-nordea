
package com.mpas.cems;
import com.mpas.cems.dao.Person;
import com.mpas.cems.util.DateProcessor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@Disabled
class RestAssuredTest {

    @BeforeEach
    void setupURL() {
        // setup the default URL and API base path to use throughout the tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/rest-classic/persons";
    }

    @Test
    void shouldReturnAListOfPersons() {
        List<Person> personList = when().get("/")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
                .and()
                .contentType(ContentType.JSON)
                .extract().response().body().jsonPath().getList("$");
        assertNotNull(personList);
        assertTrue(personList.size() >= 4);
    }

    @Test
    void shouldCreateAPerson() {
        Person person = new Person();
        person.setUsername("gigi.pedala");
        person.setFirstName("Gigi");
        person.setLastName("Pedala");
        person.setPassword("doohbeer");
        person.setHiringDate(LocalDateTime.now());
        given().body(person).when()
                .contentType(ContentType.JSON)
                .post("/")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.CREATED.value())
                .and()
                .contentType(ContentType.JSON)
                .assertThat().body("username", equalTo(person.getUsername()))
                .assertThat().body("firstName", equalTo(person.getFirstName()))
                .assertThat().body("lastName", equalTo(person.getLastName()))
                .assertThat().body("password", is(emptyOrNullString()))
                .assertThat().body("hiringDate", equalTo(DateProcessor.toString(person.getHiringDate())))
                .assertThat().body("id", not(emptyOrNullString()));
    }
}