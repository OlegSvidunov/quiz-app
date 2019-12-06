package com.blueteam.bluequiz.entities;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class Quiz {
    private int quizId;
    private String quizTitle;
    private List<Integer> questionsList = new ArrayList<>();
}