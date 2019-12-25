package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.persistence.QuizRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class QuizManagerService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizManagerService(QuizRepository quizManagerRepository) {
        this.quizRepository = quizManagerRepository;
    }

    public Optional<Quiz> findById(String id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getListOfAllQuizzes() {
        return quizRepository.findAll();
    }

    public void insert(Quiz quiz) {
        if (hasRoleAdmin()) {
            Quiz newQuiz = Quiz.builder()
                    .quizTitle(quiz.getQuizTitle())
                    .questions(quiz.getQuestions())
                    .build();
            quizRepository.insert(newQuiz);
        }
    }

    public void update(String id, Quiz quiz) {
        if (hasRoleAdmin() && quizRepository.existsById(id)) {
            quizRepository.save(Quiz.builder()
                    ._id(id)
                    .quizTitle(quiz.getQuizTitle())
                    .questions(quiz.getQuestions())
                    .build());

        }
    }

    public void deleteById(String id) {
        if (hasRoleAdmin()) {
            quizRepository.deleteById(id);
        }
    }

    private boolean hasRoleAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getAuthorities().toArray()[0].toString().equals("ADMIN")) {
            log.debug("User " + authentication.getName() + " try to call this method without grant ADMIN");
            return false;
        }
        return true;
    }
}
