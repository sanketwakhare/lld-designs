package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Bill extends BaseModel {

    private double charges;
    private ExitGate exitGate;
    private Operator operator;
    private Ticket ticket;
    private Date exitTime;
    private BillPaymentStatus billPaymentStatus;

}
