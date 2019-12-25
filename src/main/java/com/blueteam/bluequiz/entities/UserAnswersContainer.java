package com.blueteam.bluequiz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswersContainer {
    private String emailAddress;
    private Map<String, Set<String>> questionIdToAnswerId;
}