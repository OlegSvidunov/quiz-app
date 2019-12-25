package com.blueteam.bluequiz;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;

import static com.jayway.restassured.RestAssured.given;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ApplicationTests {

    @LocalServerPort
    protected int port;
    protected SessionFilter sessionFilter;

    @PostConstruct
    public void prepare() {
        RestAssured.port = port;
        sessionFilter = login();
        RestAssured.filters(sessionFilter);
    }

    private SessionFilter login() {
        SessionFilter authFilter = new SessionFilter();

        given()
                .formParam("username", "admin")
                .formParam("password", "admin")
                .filter(authFilter)
                .when()
                .post("/login").prettyPeek();
        return authFilter;
    }

    @Test
    void contextLoads() {
    }

}
