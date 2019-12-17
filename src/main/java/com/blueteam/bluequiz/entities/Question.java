package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document
public class Question {
    private final String _id = new ObjectId().toString();
    private final String questionTitle;
    private final List<Answer> questionAnswers;
}