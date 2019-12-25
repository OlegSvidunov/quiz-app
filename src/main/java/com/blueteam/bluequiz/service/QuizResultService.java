package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.*;
import com.blueteam.bluequiz.persistence.QuizRepository;
import com.blueteam.bluequiz.persistence.QuizResultRepository;
import com.blueteam.bluequiz.persistence.StatisticRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@Service
public class QuizResultService {

    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;
    private final StatisticRepository statisticRepository;
    private final QuizUserService userService;


    public QuizResultService(QuizResultRepository quizResultRepository,
                             QuizRepository quizRepository,
                             StatisticRepository statisticRepository,
                             QuizUserService userService) {
        this.quizRepository = quizRepository;
        this.quizResultRepository = quizResultRepository;
        this.statisticRepository = statisticRepository;
        this.userService = userService;
    }

    public List<QuizResult> findAll() {
        return statisticRepository.findAll();
    }

    public Quiz findQuiz(String quizId) {
        return quizRepository.findById(quizId).orElseThrow(IllegalStateException::new);
    }

    public Set<String> getCorrectAnswers (Question question) {
        return question.getQuestionAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::get_id)
                .collect(Collectors.toSet());
    }

    public QuizResult checkCorrectAnswers(String quizId, UserAnswersContainer userAnswersContainer) {
        String emailAddress = userService.getCurrentUserName();

        Quiz theQuiz = findQuiz(quizId);
        List<Question> questions = theQuiz.getQuestions();

        int questionsInQuiz = questions.size();
        int correctAnswers = 0;

        for (Question question : questions) {
            Set<String> userAnswers = userAnswersContainer.getQuestionAnswer().get(question.get_id());
            Set<String> correctAnswerIdsFromDb = getCorrectAnswers(question);

            if (correctAnswerIdsFromDb.containsAll(userAnswers)) {
                correctAnswers += 1;
            }
        }

        double calculateResults = calculateResults(questionsInQuiz, correctAnswers);
        return saveQuizResult(theQuiz, calculateResults, emailAddress);
    }

    private double calculateResults(int questionsInQuizQuantity, int usersCorrectAnswersQuantity) {
        double result = (double) usersCorrectAnswersQuantity / questionsInQuizQuantity;
        double roundedResult = BigDecimal.valueOf(result)
                .setScale(2, RoundingMode.FLOOR).doubleValue();

        return roundedResult * 100;
    }

    private QuizResult saveQuizResult(Quiz theQuiz, double calculateResults, String emailAddress) {
        QuizResult quizResult = QuizResult.builder()
                .quizId(theQuiz.get_id())
                .quizName(theQuiz.getQuizTitle())
                .userEmail(emailAddress)
                .result(calculateResults)
                .finishedTime(LocalDateTime.now())
                .build();

        return quizResultRepository.save(quizResult);
    }
}
