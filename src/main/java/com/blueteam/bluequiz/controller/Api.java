package com.blueteam.bluequiz.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Api {

    public static final String BASE_URL_TEMPLATE = "/api";

    public static final String QUIZ_URL_TEMPLATE = "/quiz/{id}";
    public static final String LIST_ALL_QUIZZES_URL_TEMPLATE = "/quiz/all";
    public static final String ADD_QUIZ_URL_TEMPLATE = "/quiz/add";
    public static final String UPDATE_QUIZ_URL_TEMPLATE = "/quiz/{id}";
    public static final String DELETE_QUIZ_URL_TEMPLATE = "/quiz/{id}";

    public static final String ADMIN_STATISTIC_URL_TEMPLATE = "/administration/quiz/statistic";
    public static final String USER_STATISTIC_URL_TEMPLATE = "/quiz/statistic";

    public static final String USER_URL_TEMPLATE = "/user";
    public static final String GET_USER_NAME_URL_TEMPLATE = "/user/name";
    public static final String GET_USER_ROLE_URL_TEMPLATE = "/user/role";
    public static final String ADD_USER_URL_TEMPLATE = "/user/add";
    public static final String LIST_ALL_USERS_URL_TEMPLATE = "/user/all";
}
