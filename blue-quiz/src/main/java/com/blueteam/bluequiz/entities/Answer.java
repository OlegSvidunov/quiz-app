package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@Builder
public class Answer {
    private int answerId;
    private String answerTitle;
    private Map<Integer, Boolean> correctAnswersMap = new HashMap<>();
}