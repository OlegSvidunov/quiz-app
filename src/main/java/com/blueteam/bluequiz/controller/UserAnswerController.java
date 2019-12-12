package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.entities.UserAnswer;
import com.blueteam.bluequiz.service.QuizResultService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.QUIZ_URL_TEMPLATE;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class UserAnswerController {

    private final QuizResultService quizResultService;

    public UserAnswerController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping(QUIZ_URL_TEMPLATE)
    public QuizResult addAnswer(@PathVariable Integer quizId,
                                @PathVariable String email,
                                @RequestBody UserAnswer userAnswer) {

        Double result = quizResultService.getQuizResults(quizId, email, userAnswer);

        return QuizResult.builder()
                .quizId(quizId)
                .result(result)
                .userEmail(email)
                .finishedTime(LocalDateTime.now())
                .build();
    }
}
