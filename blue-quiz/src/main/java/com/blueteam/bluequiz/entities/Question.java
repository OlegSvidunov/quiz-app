package com.blueteam.bluequiz.entities;


import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Question {
    private int questionId;
    private String questionTitle;
    private List<Answer> questionAnswers;
}