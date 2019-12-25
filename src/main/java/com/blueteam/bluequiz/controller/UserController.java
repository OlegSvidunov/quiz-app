package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.blueteam.bluequiz.controller.Api.*;

@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class UserController {

    final UserService quizUserService;

    public UserController(UserService quizUserService) {
        this.quizUserService = quizUserService;
    }

    @GetMapping(GET_USER_NAME_URL_TEMPLATE)
    public String getCurrentUserName() {
        return quizUserService.getCurrentUserName();
    }

    @GetMapping(GET_USER_ROLE_URL_TEMPLATE)
    public String getCurrentUserRole() {
        return quizUserService.getCurrentUserRole();
    }

    @DeleteMapping(USER_URL_TEMPLATE)
    public void deleteUsedById(String id) {
        quizUserService.deleteUserById(id);
    }

    @PostMapping(ADD_USER_URL_TEMPLATE)
    public ResponseEntity<String> saveNewUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        if (quizUserService.saveNewUser(username, password, "USER")) {
            response.addHeader("Location", "/login");
        } else {
            response.addHeader("Location", "/registration/error");
        }
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);

    }
}