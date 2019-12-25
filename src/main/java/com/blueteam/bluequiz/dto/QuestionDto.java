package com.blueteam.bluequiz.dto;

import com.blueteam.bluequiz.entities.Answer;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionDto {
    private String _id;
    private final String questionTitle;
    private final List<Answer> questionAnswers;
}