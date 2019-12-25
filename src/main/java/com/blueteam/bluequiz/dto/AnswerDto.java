package com.blueteam.bluequiz.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
@Builder
@JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AnswerDto {

    private String _id;
    private final String answerTitle;
    private final boolean isCorrect;

    public AnswerDto(String _id, String answerTitle, boolean isCorrect) {
        this._id = _id == null
                ? new ObjectId().toString()
                : _id;
        this.answerTitle = answerTitle;
        this.isCorrect = isCorrect;
    }
}