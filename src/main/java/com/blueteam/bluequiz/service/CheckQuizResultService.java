package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.*;
import com.blueteam.bluequiz.persistence.QuizRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
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

    public Set<String> getCorrectAnswers(Question question) {
        return question.getQuestionAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::get_id)
                .collect(Collectors.toSet());
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
            Set<String> userAnswers = userAnswersContainer.getQuestionIdToAnswerId().get(question.get_id());
            Set<String> correctAnswerIdsFromDb = getCorrectAnswers(question);

            if (correctAnswerIdsFromDb.containsAll(userAnswers)) {
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
