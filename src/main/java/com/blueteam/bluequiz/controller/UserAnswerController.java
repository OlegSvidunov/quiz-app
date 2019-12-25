package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.entities.UserAnswersContainer;
import com.blueteam.bluequiz.service.CheckQuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.QUIZ_URL_TEMPLATE;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
@RequiredArgsConstructor
public class UserAnswerController {

    private final CheckQuizResultService checkQuizResultService;


    @PostMapping(QUIZ_URL_TEMPLATE)
    public QuizResult addAnswer(@PathVariable String id,
                                @RequestBody UserAnswersContainer userAnswersContainer) {
        return checkQuizResultService.checkPassedQuiz(id, userAnswersContainer);
    }
}
