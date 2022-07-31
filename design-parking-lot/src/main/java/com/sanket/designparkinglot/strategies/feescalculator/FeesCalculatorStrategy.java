package com.sanket.designparkinglot.strategies.feescalculator;

import com.sanket.designparkinglot.models.Ticket;

public interface FeesCalculatorStrategy {

    double calculateFees(Ticket ticket);
}
