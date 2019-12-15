package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizManagerService {

    private final QuizRepository repository;

    @Autowired
    public QuizManagerService(QuizRepository quizManagerRepository) {
        this.repository = quizManagerRepository;
    }

    public Optional<Quiz> findById(String id) {
        return repository.findById(id);
    }

    public List<Quiz> getListOfAllQuizzes() {
        return repository.findAll();
    }

    public void insert(Quiz quiz) {
        Quiz newQuiz = Quiz.builder()
                .quizTitle(quiz.getQuizTitle())
                .questions(quiz.getQuestions())
                .build();
        repository.insert(newQuiz);
    }

    public void update(String id, Quiz quiz) {
        if (repository.existsById(id)) {
            repository.save(Quiz.builder()
                    ._id(id)
                    .quizTitle(quiz.getQuizTitle())
                    .build());
        }
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
