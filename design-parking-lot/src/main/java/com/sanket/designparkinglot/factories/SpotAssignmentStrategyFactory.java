package com.sanket.designparkinglot.factories;

import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SpotAssignmentStrategyFactory {

    private final Map<SpotAssignmentStrategyType, SpotAssignmentStrategy> registry;

    @Autowired
    public SpotAssignmentStrategyFactory(Set<SpotAssignmentStrategy> strategies) {
        registry = new HashMap<>();
        strategies.forEach(strategy -> {
            SpotAssignmentStrategyType spotAssignmentStrategyType = strategy.getStrategyType();
            register(spotAssignmentStrategyType, strategy);
        });
    }

    public SpotAssignmentStrategy get(SpotAssignmentStrategyType spotAssignmentStrategyType) {
        return registry.get(spotAssignmentStrategyType);
    }

    public void register(SpotAssignmentStrategyType spotAssignmentStrategyType, SpotAssignmentStrategy spotAssignmentStrategy) {
        registry.put(spotAssignmentStrategyType, spotAssignmentStrategy);
    }
}
