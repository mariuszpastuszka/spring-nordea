
package com.mpas.cems;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Disabled("There is a bug in the current version of RestAssured -> CsrfFilter - Invalid CSRF token found for http://localhost:59459/login")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BootSecureAppTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
     void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void johnShouldHaveAccessToPersons() {
        FormAuthConfig cfg = new FormAuthConfig("/login", "username", "password")
            .withLoggingEnabled();

        String responseStr =  given()
                .contentType(ContentType.URLENC)
                .auth().form("john","doe", cfg.withAutoDetectionOfCsrf())

                .when().get("/persons/list")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
                .extract().body().asString();

        assertAll(
                () -> assertTrue(responseStr.contains("div class=\"persons\"")),
                () -> assertTrue(responseStr.contains("sherlock.holmes")),
                () -> assertTrue(responseStr.contains("nancy.drew"))
        );
    }
}
