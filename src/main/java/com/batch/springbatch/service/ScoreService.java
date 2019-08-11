package com.batch.springbatch.service;

import com.batch.springbatch.entity.Score;
import com.batch.springbatch.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> saveScore(List<Score> scores) {
        return scoreRepository.saveAll(scores);
    }
}
