package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Quiz {
    private final int quizId;
    private String quizTitle;
    private final List<Question> questions;
}