package com.blueteam.bluequiz.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz_id_sequence")
@Getter
public class QuizIdSequence {
    @Id
    private String id;
    private Integer seq;
}
