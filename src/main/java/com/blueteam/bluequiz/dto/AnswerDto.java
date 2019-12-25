package com.blueteam.bluequiz.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AnswerDto {
    private String _id;
    private final String answerTitle;
    private final boolean isCorrect;
}