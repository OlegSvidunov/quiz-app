package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document
public class Quiz {
    @Id
    private String _id;
    private String quizTitle;
    private final List<Question> questions;
}