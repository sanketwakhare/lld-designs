package com.sanket.designparkinglot.factories.feescalculationstrategyfactory;

import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculationStrategyType;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class FeesCalculationStrategyFactory {

    private final Map<FeesCalculationStrategyType, FeesCalculatorStrategy> registry;

    @Autowired
    public FeesCalculationStrategyFactory(Set<FeesCalculatorStrategy> strategies) {
        registry = new HashMap<>();
        strategies.forEach(strategy -> {
            FeesCalculationStrategyType feesCalculationStrategyType = strategy.getStrategyType();
            register(feesCalculationStrategyType, strategy);
        });
    }

    public FeesCalculatorStrategy get(FeesCalculationStrategyType feesCalculationStrategyType) {
        return registry.get(feesCalculationStrategyType);
    }

    public void register(FeesCalculationStrategyType feesCalculationStrategyType, FeesCalculatorStrategy feesCalculatorStrategy) {
        registry.put(feesCalculationStrategyType, feesCalculatorStrategy);
    }
}
