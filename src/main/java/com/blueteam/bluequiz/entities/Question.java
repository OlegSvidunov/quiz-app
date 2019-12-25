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
    private String _id;
    private final String questionTitle;
    private final List<Answer> questionAnswers;

    public Question(String _id, String questionTitle, List<Answer> questionAnswers) {
        this._id = _id == null
                ? new ObjectId().toString()
                : _id;
        this.questionTitle = questionTitle;
        this.questionAnswers = questionAnswers;
    }
}