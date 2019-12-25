package com.blueteam.bluequiz.dto;

import com.blueteam.bluequiz.entities.Answer;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Builder
public class QuestionDto {
    private String _id;
    private final String questionTitle;
    private final List<Answer> questionAnswers;

    public QuestionDto(String _id, String questionTitle, List<Answer> questionAnswers) {
        this._id = _id == null
                ? new ObjectId().toString()
                : _id;
        this.questionTitle = questionTitle;
        this.questionAnswers = questionAnswers;
    }
}