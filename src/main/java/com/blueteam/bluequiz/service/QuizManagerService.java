package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.entities.Quiz;
import com.blueteam.bluequiz.entities.QuizIdSequence;
import com.blueteam.bluequiz.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class QuizManagerService {

    private final QuizRepository repository;
    private final MongoOperations mongoOperations;

    @Autowired
    public QuizManagerService(QuizRepository quizManagerRepository,
                              MongoOperations mongoOperations) {
        this.repository = quizManagerRepository;
        this.mongoOperations = mongoOperations;
    }

    public Optional<Quiz> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Quiz> getListOfAllQuizzes() {
        return repository.findAll();
    }

    public void insert(Quiz quiz) {
        Quiz newQuiz = Quiz.builder()
                .quizId(getNextQuizId())
                .quizTitle(quiz.getQuizTitle())
                .questions(quiz.getQuestions())
                .build();
        repository.insert(newQuiz);
    }

    public void update(Integer id, Quiz quiz) {
        if (repository.existsById(id)) {
            repository.save(Quiz.builder()
                    .quizId(id)
                    .quizTitle(quiz.getQuizTitle())
                    .build());
        }
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private Integer getNextQuizId() {
        QuizIdSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(Quiz.ID_SEQUENCE_NAME)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                QuizIdSequence.class);

        return Objects.nonNull(counter)
                ? counter.getSeq()
                : 1;
    }
}
