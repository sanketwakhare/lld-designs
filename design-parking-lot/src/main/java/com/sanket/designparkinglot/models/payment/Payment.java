package com.sanket.designparkinglot.models.payment;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.bill.Bill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String refId;

    Date paymentDate;

    private double amount;

    @ManyToOne
    private Bill bill;

}
