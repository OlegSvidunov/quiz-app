package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.service.QuizUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping(ADD_USER_URL_TEMPLATE)
    public ResponseEntity saveNewUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        quizUserService.saveNewUser(username, password, "USER");
        response.addHeader("Location", "/login");
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}