package com.blueteam.bluequiz.persistence;

import com.blueteam.bluequiz.entities.QuizResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends MongoRepository<QuizResult, Integer> {
    List<QuizResult> findByUserEmail(String userEmail);
}
