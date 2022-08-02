
package com.mpas.cems.practice.boot;

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
    void testList() throws Exception {
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
    void testShow() throws Exception {
        // TODO 50. Write a test to check that checks that requesting "/persons/1" generates the appropriate response
    }

    @Test
    void testError() throws Exception {
        // TODO 51. Write a test to check that checks that requesting "/persons/99" generates the appropriate response
    }
}
