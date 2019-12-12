package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.blueteam.bluequiz.entities.UserAnswer;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;


class UserAnswerControllerTest extends ApplicationTests {


    @Test
    public void postUserAnswer() {

        Map<Integer, Integer> question = new HashMap<>();
        question.put(1, 1);

        UserAnswer userAnswer = UserAnswer.builder()
                .questionAnswer(question)
                .build();

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("quizId", 1, "email", "test@test.com")
                .body(userAnswer)
                .post(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo(1))
                .body("userEmail", Matchers.equalTo("test@test.com"));
    }
}
