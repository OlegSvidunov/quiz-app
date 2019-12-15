package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.service.QuizResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.blueteam.bluequiz.controller.Api.*;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class StatisticController {
    final QuizResultService service;

    public StatisticController(QuizResultService service) {
        this.service = service;
    }

    @GetMapping(ADMIN_STATISTIC_URL_TEMPLATE)
    public List<QuizResult> getQuizResultList() {
        return service.findAll();
    }
}
