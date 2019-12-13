package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.entities.UserAnswersContainer;
import com.blueteam.bluequiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.QUIZ_URL_TEMPLATE;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
@RequiredArgsConstructor
public class UserAnswerController {

    private final QuizService quizService;


    @PostMapping(QUIZ_URL_TEMPLATE)
    public QuizResult addAnswer(@PathVariable Integer quizId,
                                @PathVariable String email,
                                @RequestBody UserAnswersContainer userAnswersContainer) {

        Double result = quizService.getCalculatedScore(quizId, userAnswersContainer);

        return QuizResult.builder()
                .quizId(quizId)
                .result(result)
                .userEmail(email)
                .finishedTime(LocalDateTime.now())
                .build();
    }
}
