package com.blueteam.bluequiz.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Api {

    public static final String BASE_URL_TEMPLATE = "/api";
    public static final String QUIZ_URL_TEMPLATE = "/quiz/{quizId}/email/{email}";
    public static final String STATISTIC_URL_TEMPLATE = "/statistic";
}
