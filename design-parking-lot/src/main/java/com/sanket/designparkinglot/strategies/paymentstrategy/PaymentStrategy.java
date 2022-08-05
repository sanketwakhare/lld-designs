package com.sanket.designparkinglot.strategies.paymentstrategy;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.payment.PaymentMode;

public interface PaymentStrategy {

    PaymentMode getStrategyType();

    Payment payBill(Bill bill);
}
