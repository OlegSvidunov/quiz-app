package com.blueteam.bluequiz.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document
public class Quiz {
    @Transient
    public static final String ID_SEQUENCE_NAME = "quiz_id_sequence";
    @Id
    private final int quizId;
    private String quizTitle;
    private final List<Question> questions;
}