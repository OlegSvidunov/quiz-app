package com.blueteam.bluequiz.persistence;

import com.blueteam.bluequiz.entities.QuizResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticRepository {
    private final MongoTemplate mongoTemplate;

    public StatisticRepository(MongoTemplate template) {
        this.mongoTemplate = template;
    }
    public List<QuizResult> findAll(){
        return mongoTemplate.findAll(QuizResult.class);
    }
}
