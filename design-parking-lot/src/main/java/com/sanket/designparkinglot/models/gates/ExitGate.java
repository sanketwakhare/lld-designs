package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.bill.BillPaymentStatus;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.strategies.feescalculator.FeesCalculatorStrategy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Calendar;

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
        // TODO: handle exceptions
        Bill bill = new Bill();
        bill.setExitGate(this);
        bill.setBillPaymentStatus(BillPaymentStatus.UNPAID);
        bill.setOperator(this.getOperator());
        bill.setExitTime(Calendar.getInstance().getTime());
        bill.setCharges(feesCalculatorStrategy.calculateFees(ticket));
        bill.setTicket(ticket);
        return bill;
    }
}
