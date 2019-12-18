package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.given;


class StatisticControllerTest extends ApplicationTests {

    @Autowired
    StatisticRepository statisticRepository;

    @BeforeEach
    void setup() {
        saveStatisticsTestData();
    }

    @AfterEach
    void clearData() {
        statisticRepository.deleteById("1");
        statisticRepository.deleteById("2");
        statisticRepository.deleteById("3");
    }

    @Test
    public void shouldReturnStatistic() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(Api.BASE_URL_TEMPLATE + Api.ADMIN_STATISTIC_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    private void saveStatisticsTestData() {

        QuizResult quizResult1 = QuizResult.builder()
                ._id("1")
                .quizId("1")
                .userEmail("test@test.com")
                .result(50.0)
                .build();
        QuizResult quizResult2 = QuizResult.builder()
                ._id("2")
                .quizId("2")
                .userEmail("com@test.com")
                .result(17.0)
                .build();
        QuizResult quizResult3 = QuizResult.builder()
                ._id("3")
                .quizId("3")
                .userEmail("example@test.com")
                .result(75.0)
                .build();

        statisticRepository.save(quizResult1);
        statisticRepository.save(quizResult2);
        statisticRepository.save(quizResult3);
    }
}