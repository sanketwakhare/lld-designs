package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.payment.MakePaymentRequestDto;
import com.sanket.designparkinglot.dtos.payment.MakePaymentResponseDto;
import com.sanket.designparkinglot.exceptions.NoBillException;
import com.sanket.designparkinglot.models.payment.Payment;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import com.sanket.designparkinglot.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        MakePaymentResponseDto makePaymentResponseDto = new MakePaymentResponseDto();
        long billId = makePaymentRequestDto.getBillId();
        PaymentMode paymentMode = makePaymentRequestDto.getPaymentMode();
        try {
            Payment payment = paymentService.makePayment(billId, paymentMode);
            makePaymentResponseDto.setPayment(payment);
            makePaymentResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | NoBillException e) {
            makePaymentResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            makePaymentResponseDto.setMessage(e.getMessage());
        }
        return makePaymentResponseDto;
    }
}
