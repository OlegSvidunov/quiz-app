package com.blueteam.bluequiz.persistence;

import com.blueteam.bluequiz.entities.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
    void deleteByQuizTitle(String title);
}
