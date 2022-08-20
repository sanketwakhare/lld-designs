package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Payment extends BaseModel {
    private String refId;
    @OneToOne
    private Ticket ticket;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
}
