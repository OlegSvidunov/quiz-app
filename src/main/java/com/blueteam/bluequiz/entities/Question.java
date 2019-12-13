package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document
public class Question {
    @Id
    private final int questionId;
    private final String questionTitle;
    private final List<Answer> questionAnswers;


    public Answer getCorrectAnswer() {
        return questionAnswers.stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(IllegalStateException::new);

    }
}