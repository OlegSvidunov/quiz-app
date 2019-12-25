package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.ApplicationTests;
import com.blueteam.bluequiz.entities.Answer;
import com.blueteam.bluequiz.entities.Question;
import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.entities.UserAnswersContainer;
import com.blueteam.bluequiz.persistence.QuizRepository;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static com.jayway.restassured.RestAssured.given;

public class UserAnswersControllerTest extends ApplicationTests {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    StatisticRepository statisticRepository;


    @BeforeEach
    void setup() {
        addTestQuizData();
    }

    @AfterEach
    void clearData() {
        quizRepository.deleteById("3");
        statisticRepository.deleteByUserEmail("admin");
    }

    @Test
    public void shouldGet100PercentWithAllCorrectAnswers() {

        Set answer1 = new HashSet();
        answer1.add("2");
        Set answer2 = new HashSet();
        answer2.add("5");
        Set answer3 = new HashSet();
        answer3.add("8");

        Map<String, Set<String>> question = new HashMap<>();
        question.put("1", answer1);
        question.put("2", answer2);
        question.put("3", answer3);

        UserAnswersContainer userAnswersContainer = UserAnswersContainer.builder()
                .questionIdToAnswerId(question)
                .emailAddress("admin")
                .build();

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("id", 3)
                .body(userAnswersContainer)
                .post(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo("3"))
                .body("result", Matchers.equalTo(100.0f));
    }

    @Test
    public void shouldGet66PercentWith1CorrectAnswerOf3() {

        Set answer1 = new HashSet();
        answer1.add("2");
        Set answer2 = new HashSet();
        answer2.add("6");
        Set answer3 = new HashSet();
        answer3.add("8");

        Map<String, Set<String>> question = new HashMap<>();
        question.put("1", answer1);
        question.put("2", answer2);
        question.put("3", answer3);

        UserAnswersContainer userAnswersContainer = UserAnswersContainer.builder()
                .questionIdToAnswerId(question)
                .emailAddress("admin")
                .build();

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("id", 3)
                .body(userAnswersContainer)
                .post(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo("3"))
                .body("result", Matchers.equalTo(66.0f));
    }

    @Test
    public void shouldGet0PercentWithoutCorrectAnswers() {

        Set answer1 = new HashSet();
        answer1.add("1");
        Set answer2 = new HashSet();
        answer2.add("6");
        Set answer3 = new HashSet();
        answer3.add("9");

        Map<String, Set<String>> question = new HashMap<>();
        question.put("1", answer1);
        question.put("2", answer2);
        question.put("3", answer3);

        UserAnswersContainer userAnswersContainer = UserAnswersContainer.builder()
                .questionIdToAnswerId(question)
                .emailAddress("admin")
                .build();

        given()
                .contentType(ContentType.JSON)
                .when()
                .pathParameters("id", 3)
                .body(userAnswersContainer)
                .post(Api.BASE_URL_TEMPLATE + Api.QUIZ_URL_TEMPLATE)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("quizId", Matchers.equalTo("3"))
                .body("result", Matchers.equalTo(0.0f));
    }


    private void addTestQuizData() {
        quizRepository.save(Quiz.builder()
                ._id("3")
                .quizTitle("quiz1")
                .questions(Arrays.asList(Question.builder()
                                ._id("1")
                                .questionTitle("title1")
                                .questionAnswers(Arrays.asList(Answer.builder()
                                                ._id("1")
                                                .answerTitle("answerTitle1")
                                                .isCorrect(false)
                                                .build(),
                                        Answer.builder()
                                                ._id("2")
                                                .answerTitle("answerTitle2")
                                                .isCorrect(true)
                                                .build(),
                                        Answer.builder()
                                                ._id("3")
                                                .answerTitle("answerTitle3")
                                                .isCorrect(false)
                                                .build()))
                                .build(),
                        Question.builder()
                                ._id("2")
                                .questionTitle("title2")
                                .questionAnswers(Arrays.asList(Answer.builder()
                                                ._id("4")
                                                .answerTitle("answerTitle4")
                                                .isCorrect(false)
                                                .build(),
                                        Answer.builder()
                                                ._id("5")
                                                .answerTitle("answerTitle5")
                                                .isCorrect(true)
                                                .build(),
                                        Answer.builder()
                                                ._id("6")
                                                .isCorrect(false)
                                                .answerTitle("answerTitle6")
                                                .build()))
                                .build(),
                        Question.builder()
                                ._id("3")
                                .questionTitle("title3")
                                .questionAnswers(Arrays.asList(Answer.builder()
                                                ._id("7")
                                                .answerTitle("answerTitle7")
                                                .isCorrect(false)
                                                .build(),
                                        Answer.builder()
                                                ._id("8")
                                                .answerTitle("answerTitle8")
                                                .isCorrect(true)
                                                .build(),
                                        Answer.builder()
                                                ._id("9")
                                                .isCorrect(false)
                                                .answerTitle("answerTitle9")
                                                .build()))
                                .build()))
                .build());
    }
}
