package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
public class Answer {
    private final String _id = new ObjectId().toString();
    private final String answerTitle;
    private final boolean isCorrect;
}