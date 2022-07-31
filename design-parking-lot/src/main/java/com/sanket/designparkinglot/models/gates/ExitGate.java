package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;

public class ExitGate extends Gate {

    FeesCalculatorStrategy feesCalculatorStrategy;

    public ExitGate(FeesCalculatorStrategy feesCalculatorStrategy) {
        super(GateType.EXIT);
        this.feesCalculatorStrategy = feesCalculatorStrategy;
    }

    public Bill generateBill(Ticket ticket) {
        // TODO: implement method
        return null;
    }
}
