package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.dto.QuizDto;
import com.blueteam.bluequiz.entities.Quiz;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

    public Quiz transformQuizDtoToQuizEntity(QuizDto quizDto) {
        return Quiz.builder()
                ._id(quizDto.get_id())
                .quizTitle(quizDto.getQuizTitle())
                .questions(quizDto.getQuestions())
                .build();
    }

}
