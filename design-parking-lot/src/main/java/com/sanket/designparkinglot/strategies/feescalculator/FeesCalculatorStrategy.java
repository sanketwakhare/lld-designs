package com.sanket.designparkinglot.strategies.feescalculator;

import com.sanket.designparkinglot.models.ticket.Ticket;

public interface FeesCalculatorStrategy {

    FeesCalculationStrategyType getStrategyType();

    double calculateFees(Ticket ticket);
}
