package com.sanket.designparkinglot.dtos.bill;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBillRequestDto extends CreateRequestDto {

    private long gateId;
    private long ticketId;
    private FeesCalculationStrategyType feesCalculationStrategyType;
}
