package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.entities.Quiz;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blueteam.bluequiz.service.QuizManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.blueteam.bluequiz.controller.Api.*;


@Log4j
@RestController
@RequestMapping(BASE_URL_TEMPLATE)
public class QuizController {

    private final QuizManagerService service;

    @Autowired
    public QuizController(QuizManagerService service) {
        this.service = service;
    }

    @GetMapping(value = SUBMIT_QUIZ_ANSWER, produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz getById(@PathVariable Integer id) {
        return service
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @GetMapping(value = LIST_ALL_QUIZZES_URL_TEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quiz> getListOfAllQuizzes() {
        return service.getListOfAllQuizzes();
    }

    @PostMapping(value = ADD_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Quiz quiz) {
        service.insert(quiz);
    }

    @PutMapping(value = UPDATE_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer id, @RequestBody Quiz quiz) {
        service.update(id, quiz);
    }

    @DeleteMapping(value = DELETE_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
