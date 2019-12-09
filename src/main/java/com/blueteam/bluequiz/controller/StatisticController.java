package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.service.QuizResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.STATISTIC_URL_TEMPLATE;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class StatisticController {
    final QuizResultService service;

    public StatisticController(QuizResultService service) {
        this.service = service;
    }

//    @GetMapping(STATISTIC_URL_TEMPLATE)
//    public List<QuizResult> getStatistic() {
//
//        return Arrays.asList(QuizResult.builder()
//                        .id(1)
//                        .quizId(1)
//                        .quizName("quiz_name_1")
//                        .userEmail("test@test.com")
//                        .result(70.0)
//                        .finishedTime(LocalDateTime.now())
//                        .build(),
//                QuizResult.builder()
//                        .id(2)
//                        .quizId(2)
//                        .quizName("quiz_name_2")
//                        .userEmail("com@test.com")
//                        .result(50.0)
//                        .finishedTime(LocalDateTime.now())
//                        .build(),
//                QuizResult.builder()
//                        .id(3)
//                        .quizId(3)
//                        .quizName("quiz_name_3")
//                        .userEmail("example@test.com")
//                        .result(53.0)
//                        .finishedTime(LocalDateTime.now())
//                        .build()
//        );
//    }

    @GetMapping(STATISTIC_URL_TEMPLATE)
    public List<QuizResult> getQuizResultList() {
        return service.findAll();
    }
}
