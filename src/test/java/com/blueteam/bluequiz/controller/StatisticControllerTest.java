package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;


class StatisticControllerTest extends ApplicationTests {

    @Autowired
    StatisticRepository statisticRepository;

    @Test
    public void shouldReturnStatistic() {

        saveStatisticsTestData();

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(Api.BASE_URL_TEMPLATE + Api.ADMIN_STATISTIC_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].quizId", Matchers.equalTo("1"))
                .body("[0].userEmail", Matchers.equalTo("test@test.com"))
                .body("[0].result", Matchers.equalTo(50f))
                .body("[1].quizId", Matchers.equalTo("2"))
                .body("[1].userEmail", Matchers.equalTo("com@test.com"))
                .body("[1].result", Matchers.equalTo(17f))
                .body("[2].quizId", Matchers.equalTo("3"))
                .body("[2].userEmail", Matchers.equalTo("example@test.com"))
                .body("[2].result", Matchers.equalTo(75f));
    }

    private void saveStatisticsTestData() {
        List<QuizResult> results = Arrays.asList(QuizResult.builder()
                        .quizId("1")
                        .userEmail("test@test.com")
                        .result(50.0)
                        .build(),
                QuizResult.builder()
                        .quizId("2")
                        .userEmail("com@test.com")
                        .result(17.0)
                        .build(),
                QuizResult.builder()
                        .quizId("3")
                        .userEmail("example@test.com")
                        .result(75.0)
                        .build());

        statisticRepository.saveAll(results);
    }
}