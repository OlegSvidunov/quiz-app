package com.blueteam.bluequiz.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Api {

    public static final String BASE_URL_TEMPLATE = "/api";
    public static final String GET_QUIZ_BY_ID_URL_TEMPLATE = "quiz/get/{id}";
    public static final String LIST_ALL_QUIZZES_URL_TEMPLATE = "quiz/list-all";
    public static final String ADD_QUIZ_URL_TEMPLATE = "quiz/add";
    public static final String UPDATE_QUIZ_URL_TEMPLATE = "quiz/update/{id}";
    public static final String DELETE_QUIZ_URL_TEMPLATE = "quiz/delete/{id}";
    public static final String ADMIN_STATISTIC_URL_TEMPLATE = "quiz/admin-statistic";
    public static final String USER_STATISTIC_URL_TEMPLATE = "quiz/user-statistic";

}
