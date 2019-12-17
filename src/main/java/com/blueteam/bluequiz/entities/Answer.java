package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
public class Answer {

    private String _id;
    private final String answerTitle;
    private final boolean isCorrect;

    public Answer(String _id, String answerTitle, boolean isCorrect) {
        this._id = _id == null
                ? new ObjectId().toString()
                : _id;
        this.answerTitle = answerTitle;
        this.isCorrect = isCorrect;
    }
}