package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseModel {

    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String refId;
    Date paymentDate;
    private double amount;
    private Bill bill;

}
