package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.service.QuizUserService;
import com.blueteam.bluequiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.blueteam.bluequiz.controller.Api.*;

@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class UserController {

  private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(GET_USER_NAME_URL_TEMPLATE)
    public String getCurrentUserName() {
        return userService.getCurrentUserName();
    }

    @GetMapping(GET_USER_ROLE_URL_TEMPLATE)
    public String getCurrentUserRole() {
        return userService.getCurrentUserRole();
    }

    @DeleteMapping(USER_URL_TEMPLATE)
    public void deleteUsedById(String id) {
        userService.deleteUserById(id);
    }

    @PostMapping(ADD_USER_URL_TEMPLATE)
    public ResponseEntity saveNewUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        if (userService.saveNewUser(username, password, "USER")) {
            response.addHeader("Location", "/login");
        } else {
            response.addHeader("Location", "/registration/error");
        }
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);

    }
}