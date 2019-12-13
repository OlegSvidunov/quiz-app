package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
public class Answer {
    @Id
    private final Integer answerId;
    private final String answerTitle;
    private final boolean isCorrect;
}