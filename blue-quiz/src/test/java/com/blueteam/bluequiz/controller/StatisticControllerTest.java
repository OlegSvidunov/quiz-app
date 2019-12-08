package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.given;


class StatisticControllerTest extends ApplicationTests {


    @Test
    public void shouldReturnStatistic() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(Api.BASE_URL_TEMPLATE + Api.STATISTIC_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].quizId", Matchers.equalTo(1))
                .body("[0].userEmail", Matchers.equalTo("test@test.com"))
                .body("[1].quizId", Matchers.equalTo(2))
                .body("[1].userEmail", Matchers.equalTo("com@test.com"))
                .body("[2].quizId", Matchers.equalTo(3))
                .body("[2].userEmail", Matchers.equalTo("example@test.com"));
    }
}