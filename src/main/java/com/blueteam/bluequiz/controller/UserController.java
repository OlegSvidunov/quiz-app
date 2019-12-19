package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.service.QuizUserService;
import org.springframework.web.bind.annotation.*;

import static com.blueteam.bluequiz.controller.Api.*;

@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class UserController {

    final QuizUserService quizUserService;

    public UserController(QuizUserService quizUserService) {
        this.quizUserService = quizUserService;
    }

    @GetMapping(USER_URL_TEMPLATE)
    public String getCurrentUserName() {
        return quizUserService.getCurrentUserName();
    }

    @DeleteMapping(USER_URL_TEMPLATE)
    public void deleteUsedById(String id) {
        quizUserService.deleteUserById(id);
    }

    @PostMapping(LIST_ALL_QUIZZES_URL_TEMPLATE)
    public void saveNewUser(@RequestParam String userName, @RequestParam String password) {
        quizUserService.saveNewUser(userName, password, "USER");
    }
}
