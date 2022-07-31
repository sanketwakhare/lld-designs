package com.sanket.designparkinglot.models.bill;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.models.gates.ExitGate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Bill extends BaseModel {

    private Date exitTime;
    private ExitGate exitGate;
    private double charges;
    private Ticket ticket;
    private Operator operator;
    private BillPaymentStatus billPaymentStatus;

}
