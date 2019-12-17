package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
public class Answer {
    private String _id;
    private final String answerTitle;
    private final boolean isCorrect;
}