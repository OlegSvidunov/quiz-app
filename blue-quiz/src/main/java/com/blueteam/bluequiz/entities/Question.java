package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Question {

    private final int questionId;
    private final String questionTitle;
    private final List<Answer> questionAnswers;

}