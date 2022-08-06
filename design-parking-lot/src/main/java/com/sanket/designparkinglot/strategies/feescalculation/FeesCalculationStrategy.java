package com.sanket.designparkinglot.strategies.feescalculation;

import com.sanket.designparkinglot.models.ticket.Ticket;

public interface FeesCalculationStrategy {

    FeesCalculationStrategyType getStrategyType();

    double calculateFees(Ticket ticket);
}
