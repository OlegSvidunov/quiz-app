package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.Question;
import com.blueteam.bluequiz.entities.Quiz;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.QUIZ_URL_TEMPLATE;


@Log4j
@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class QuizController {

    @GetMapping(QUIZ_URL_TEMPLATE)
    public Quiz getQuizById(@PathVariable int quizId, @PathVariable String email) {
        Quiz quiz;

        if (quizId == 1) {
            quiz = Quiz.builder()
                    .quizId(1)
                    .quizTitle("quiz1")
                    .questions(Collections.singletonList(Question.builder()
                            .questionId(1)
                            .questionTitle("title1")
                            .build()))
                    .build();
        } else {
            quiz = Quiz.builder()
                    .quizId(2)
                    .quizTitle("quiz2")
                    .questions(Collections.singletonList(Question.builder()
                            .questionId(2)
                            .questionTitle("title2")
                            .build()))
                    .build();
        }

        return quiz;
    }
}
