package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.*;
import com.blueteam.bluequiz.persistence.QuizRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class CheckQuizResultService {

    private final QuizRepository quizRepository;
    private final UserService userService;
    private final SaveQuizResultService saveQuizResultService;


    public CheckQuizResultService(QuizRepository quizRepository,
                                  UserService userService, SaveQuizResultService saveQuizResultService) {
        this.quizRepository = quizRepository;
        this.userService = userService;
        this.saveQuizResultService = saveQuizResultService;
    }

    public Quiz findQuiz(String quizId) {
        return quizRepository.findById(quizId).orElseThrow(IllegalStateException::new);
    }

    public Answer getCorrectAnswer(Question question) {
        return question.getQuestionAnswers().stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public QuizResult checkPassedQuiz(String quizId, UserAnswersContainer userAnswersContainer) {
        String emailAddress = userService.getCurrentUserName();

        Quiz quiz = findQuiz(quizId);
        List<Question> questions = quiz.getQuestions();

        int questionsInQuizCount = questions.size();

        int correctAnswersCount = calculateUsersRightAnswers(userAnswersContainer, questions);

        double calculateResults = calculateResults(questionsInQuizCount, correctAnswersCount);

        return saveQuizResultService.saveQuizResult(quiz, calculateResults, emailAddress);
    }

    private int calculateUsersRightAnswers(UserAnswersContainer userAnswersContainer, List<Question> questions) {
        int correctAnswers = 0;


        for (Question question : questions) {
            String userAnswerId = userAnswersContainer.getQuestionIdToAnswerId().get(question.get_id());
            String correctAnswerIdFromDb = getCorrectAnswer(question).get_id();

            if (Objects.equals(userAnswerId, correctAnswerIdFromDb)) {
                correctAnswers += 1;
            }
        }
        return correctAnswers;
    }

    private double calculateResults(int questionsInQuizQuantity, int usersCorrectAnswersQuantity) {
        double result = (double) usersCorrectAnswersQuantity / questionsInQuizQuantity;
        double roundedResult = BigDecimal.valueOf(result)
                .setScale(2, RoundingMode.FLOOR).doubleValue();

        return roundedResult * 100;
    }

}
