package com.sanket.designparkinglot.dtos.payment;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.models.payment.PaymentMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MakePaymentRequestDto extends CreateRequestDto {

    private long billId;
    private PaymentMode paymentMode;
}
