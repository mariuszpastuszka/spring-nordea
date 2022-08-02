
package com.mpas.cems.boot.two;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebApplication3Test {

    @LocalServerPort
    private Integer port;

    @Test
    void testListPersons() throws Exception {
              String responseStr =   given().baseUri("http://localhost")
                .port(port).when().get("/persons/list")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
                .extract().body().asString();

              assertAll(
                      () -> assertTrue(responseStr.contains("div class=\"persons\"")),
                      () -> assertTrue(responseStr.contains("sherlock.holmes")),
                      () -> assertTrue(responseStr.contains("nancy.drew"))
              );
    }

    @Test
    void testShowPerson() throws Exception {
        String responseStr = given().baseUri("http://localhost")
                .port(port).when().get("/persons/1")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
                .extract().body().asString();

        assertAll(
                () -> assertTrue(responseStr.contains("sherlock.holmes")),
                () -> assertTrue(responseStr.contains("Employed since"))
        );
    }

    @Test
    void testErrorPerson() throws Exception {
        String responseStr = given().baseUri("http://localhost")
                .port(port).when().get("/persons/99")
                .then()
                .assertThat().statusCode(HttpStatus.NOT_FOUND.value())
                .extract().body().asString();

        assertAll(
                () -> assertTrue(responseStr.contains("Unexpected error")),
                () -> assertTrue(responseStr.contains("Person with id: 99 does not exist!"))
        );
    }
}
