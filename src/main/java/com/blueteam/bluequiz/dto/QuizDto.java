package com.blueteam.bluequiz.dto;

import com.blueteam.bluequiz.entities.Question;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Builder
public class QuizDto {
    @Id
    private String _id;
    private String quizTitle;
    private final List<Question> questions;
}