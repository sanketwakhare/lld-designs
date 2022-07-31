package com.sanket.designparkinglot.strategies.paymentstrategy;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.payment.Payment;

public interface PaymentStrategy {

    Payment payBill(Bill bill);
}
