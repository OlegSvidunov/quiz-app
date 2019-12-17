package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document
public class QuizResult {
    @Id
    private String _id;
    private final Integer quizId;
    private final String quizName;
    private final Double result;
    private final String userEmail;
    private LocalDateTime finishedTime;

}