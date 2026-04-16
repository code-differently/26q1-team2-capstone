package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.CopingStrategy;
import com.team2.mentalhealthtracker.repository.CopingStrategyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopingStrategyService {

    private final CopingStrategyRepository copingStrategyRepository;

    public CopingStrategyService(CopingStrategyRepository copingStrategyRepository) {
        this.copingStrategyRepository = copingStrategyRepository;
    }

    public CopingStrategy saveCopingStrategy(CopingStrategy copingStrategy) {
        return copingStrategyRepository.save(copingStrategy);
    }

    public List<CopingStrategy> getAllCopingStrategies() {
        return copingStrategyRepository.findAll();
    }
}