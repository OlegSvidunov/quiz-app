package com.blueteam.bluequiz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswersContainer {
    private String emailAddress;
    private Map<String, String> questionIdToAnswerId;
}