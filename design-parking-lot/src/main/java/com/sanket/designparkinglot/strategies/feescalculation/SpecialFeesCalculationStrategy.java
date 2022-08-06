package com.sanket.designparkinglot.strategies.feescalculation;

import com.sanket.designparkinglot.models.ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class SpecialFeesCalculationStrategy implements FeesCalculationStrategy {

    @Override
    public FeesCalculationStrategyType getStrategyType() {
        return FeesCalculationStrategyType.SPECIAL;
    }

    @Override
    public double calculateFees(Ticket ticket) {
        return 0;
    }
}
