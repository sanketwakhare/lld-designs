package com.sanket.designparkinglot.strategies.feescalculator;

import com.sanket.designparkinglot.models.ticket.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("normalFeesCalculatorStrategy")
public interface FeesCalculatorStrategy {

    double calculateFees(Ticket ticket);
}
