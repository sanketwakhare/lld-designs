package com.sanket.designparkinglot.models;

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
