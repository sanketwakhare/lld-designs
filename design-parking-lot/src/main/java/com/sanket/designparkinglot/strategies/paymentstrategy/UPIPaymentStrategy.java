package com.sanket.designparkinglot.strategies.paymentstrategy;

import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.bill.BillPaymentStatus;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.models.payment.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class UPIPaymentStrategy implements PaymentStrategy {

    private final PaymentMode paymentMode = PaymentMode.UPI;

    @Override
    public PaymentMode getStrategyType() {
        return this.paymentMode;
    }

    @Override
    public Payment payBill(Bill bill) {
        Payment payment = new Payment();
        payment.setPaymentDate(Calendar.getInstance().getTime());
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setAmount(bill.getCharges());
        payment.setBill(bill);
        payment.setRefId(RandomRefIdGenerator.generateRandomRefId(16));
        return payment;
    }
}
