package com.blueteam.bluequiz.controller;

import com.blueteam.bluequiz.dto.QuizDto;
import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.service.QuizManagerService;
import com.blueteam.bluequiz.service.TransformService;
import lombok.extern.log4j.Log4j;
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

    private final QuizManagerService quizManagerService;
    private final TransformService transformService;

    @Autowired
    public QuizController(QuizManagerService quizManagerService,
                          TransformService transformService) {
        this.quizManagerService = quizManagerService;
        this.transformService = transformService;
    }

    @GetMapping(value = QUIZ_URL_TEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz getById(@PathVariable String id) {
        return quizManagerService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = LIST_ALL_QUIZZES_URL_TEMPLATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quiz> getListOfAllQuizzes() {
        return quizManagerService.getListOfAllQuizzes();
    }

    @PostMapping(value = ADD_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody QuizDto quizDto) {
        Quiz quiz = transformService.transformQuizDtoToQuizEntity(quizDto);
        quizManagerService.insert(quiz);
    }

    @PutMapping(value = UPDATE_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable String id, @RequestBody QuizDto quizDto) {
        Quiz quiz = transformService.transformQuizDtoToQuizEntity(quizDto);
        quizManagerService.update(id, quiz);
    }

    @DeleteMapping(value = DELETE_QUIZ_URL_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable String id) {
        quizManagerService.deleteById(id);
    }
}
