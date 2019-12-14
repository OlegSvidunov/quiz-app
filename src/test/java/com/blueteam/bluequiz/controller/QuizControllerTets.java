package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.given;


class QuizControllerTest extends ApplicationTests {

    //TODO: Broken test, fix after integration with DB
    @Disabled
    @Test
    public void getFirstQuizById() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("quizId", 1, "email", "test@test.com")
                .get(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo(1));
    }

    //TODO: Broken test, fix after integration with DB
    @Disabled
    @Test
    public void getSecondQuizById() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("quizId", 2, "email", "test@test.com")
                .get(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo(2));

    }

}