package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ExitGate extends Gate {

    public ExitGate(String gateNumber) {
        super(GateType.EXIT, gateNumber);
    }

    public ExitGate() {
        super(GateType.EXIT);
    }

    public Bill generateBill(Ticket ticket,
                             FeesCalculatorStrategy feesCalculatorStrategy) {
        // TODO: implement method
        return null;
    }
}
