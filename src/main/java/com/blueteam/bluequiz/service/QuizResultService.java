package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.persistence.QuizRepository;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizResultService {
    private static List<QuizResult> quizResultList = Arrays.asList(QuizResult.builder()
                    .id(1)
                    .quizId(1)
                    .quizName("quiz_name_1")
                    .userEmail("test@test.com")
                    .result(70.0)
                    .finishedTime(LocalDateTime.now())
                    .build(),
            QuizResult.builder()
                    .id(2)
                    .quizId(2)
                    .quizName("quiz_name_2")
                    .userEmail("com@test.com")
                    .result(50.0)
                    .finishedTime(LocalDateTime.now())
                    .build(),
            QuizResult.builder()
                    .id(3)
                    .quizId(3)
                    .quizName("quiz_name_3")
                    .userEmail("example@test.com")
                    .result(53.0)
                    .finishedTime(LocalDateTime.now())
                    .build()
    );
    private final StatisticRepository statisticRepository;
    private final QuizRepository quizRepository;

    public QuizResultService(StatisticRepository statisticRepository, QuizRepository quizRepository) {
        this.statisticRepository = statisticRepository;
        this.quizRepository = quizRepository;
    }

    @PostConstruct
    public void init() {
        statisticRepository.saveAll(quizResultList);
    }

    public List<QuizResult> findAll() {
        return statisticRepository.findAll();
    }
}
