package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {
    private final int answerId;
    private final String answerTitle;
    private final boolean isCorrect;
}