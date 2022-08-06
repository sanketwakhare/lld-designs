package com.sanket.designparkinglot.dtos.bill;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.bill.Bill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBillResponseDto extends ResponseDto {

    private Bill bill;
}
