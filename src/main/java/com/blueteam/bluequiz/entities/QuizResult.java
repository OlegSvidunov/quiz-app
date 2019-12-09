package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QuizResult {
    private final Integer id;
    private final Integer quizId;
    private final String quizName;
    private final Double result;
    private final String userEmail;
    private LocalDateTime finishedTime;

}