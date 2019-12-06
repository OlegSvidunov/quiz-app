package com.blueteam.bluequiz;

import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ApplicationTests {

    @LocalServerPort
    protected int port;

    @PostConstruct
    public void prepare() {
        RestAssured.port = port;
    }


    @Test
    void contextLoads() {
    }

}
