package com.sanket.designparkinglot.models.payment;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.bill.Bill;
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
