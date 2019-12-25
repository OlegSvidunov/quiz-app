package com.blueteam.bluequiz.controller;


import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.blueteam.bluequiz.controller.Api.ADMIN_STATISTIC_URL_TEMPLATE;
import static com.blueteam.bluequiz.controller.Api.BASE_URL_TEMPLATE;


@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(ADMIN_STATISTIC_URL_TEMPLATE)
    public List<QuizResult> getQuizResultList() {
        return statisticService.findAll();
    }
}
