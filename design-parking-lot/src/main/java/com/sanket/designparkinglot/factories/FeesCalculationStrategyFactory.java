package com.sanket.designparkinglot.factories;

import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategyType;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class FeesCalculationStrategyFactory {

    private final Map<FeesCalculationStrategyType, FeesCalculationStrategy> registry;

    @Autowired
    public FeesCalculationStrategyFactory(Set<FeesCalculationStrategy> strategies) {
        registry = new HashMap<>();
        strategies.forEach(strategy -> {
            FeesCalculationStrategyType feesCalculationStrategyType = strategy.getStrategyType();
            register(feesCalculationStrategyType, strategy);
        });
    }

    public FeesCalculationStrategy get(FeesCalculationStrategyType feesCalculationStrategyType) {
        return registry.get(feesCalculationStrategyType);
    }

    public void register(FeesCalculationStrategyType feesCalculationStrategyType, FeesCalculationStrategy feesCalculationStrategy) {
        registry.put(feesCalculationStrategyType, feesCalculationStrategy);
    }
}
