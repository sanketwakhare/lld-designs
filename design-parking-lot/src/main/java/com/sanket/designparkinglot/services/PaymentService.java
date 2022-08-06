package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoBillException;
import com.sanket.designparkinglot.factories.PaymentStrategyFactory;
import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.models.bill.BillPaymentStatus;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.models.payment.PaymentStatus;
import com.sanket.designparkinglot.repositories.BillRepository;
import com.sanket.designparkinglot.repositories.PaymentRepository;
import com.sanket.designparkinglot.strategies.paymentstrategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService extends BaseService {

    private final PaymentRepository paymentRepository;

    private final BillRepository billRepository;


    private final PaymentStrategyFactory paymentStrategyFactory;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BillRepository billRepository, PaymentStrategyFactory paymentStrategyFactory) {
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    public Payment makePayment(long billId, PaymentMode paymentMode) throws NoBillException {

        PaymentStrategy paymentStrategy = paymentStrategyFactory.get(paymentMode);

        Optional<Bill> dbBill = billRepository.findById(billId);
        if (dbBill.isEmpty()) {
            throw new NoBillException(billId);
        }
        // make payment
        Bill bill = dbBill.get();
        Payment payment = paymentStrategy.payBill(bill);
        setCreateModelDefaults(payment);
        Payment dbPayment = paymentRepository.save(payment);

        // update bill payment status
        if (PaymentStatus.SUCCESS.equals(dbPayment.getPaymentStatus())) {
            bill.setBillPaymentStatus(BillPaymentStatus.PAID);
            setUpdateModelDefaults(bill);
            billRepository.save(bill);
        }
        return dbPayment;
    }
}
