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

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(USER_URL_TEMPLATE)
    public String getCurrentUserName() {
        return userService.getCurrentUserName();
    }

    @DeleteMapping(USER_URL_TEMPLATE)
    public void deleteUsedById(String id) {
        userService.deleteUserById(id);
    }

    @PostMapping(ADD_USER_URL_TEMPLATE)
    public ResponseEntity saveNewUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        userService.saveNewUser(username, password, "USER");
        response.addHeader("Location", "/login");
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}