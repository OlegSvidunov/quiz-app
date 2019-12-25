package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;


    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<QuizResult> findAll() {
        return statisticRepository.findAll();
    }

}
