package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.entities.QuizResult;
import com.blueteam.bluequiz.persistence.QuizResultRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j
@Service
public class SaveQuizResultService {

    private final QuizResultRepository quizResultRepository;

    public SaveQuizResultService(QuizResultRepository quizResultRepository) {
        this.quizResultRepository = quizResultRepository;
    }

    public QuizResult saveQuizResult(Quiz theQuiz, double calculateResults, String emailAddress) {
        QuizResult quizResult = QuizResult.builder()
                .quizId(theQuiz.get_id())
                .quizName(theQuiz.getQuizTitle())
                .userEmail(emailAddress)
                .result(calculateResults)
                .finishedTime(LocalDateTime.now())
                .build();

        return quizResultRepository.save(quizResult);
    }
}
