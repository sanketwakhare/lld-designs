package com.sanket.designparkinglot.dtos.gate;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.models.gates.GateStatus;
import com.sanket.designparkinglot.models.gates.GateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateGateRequestDto extends CreateRequestDto {

    private String gateNumber;
    private GateType gateType;
    private GateStatus gateStatus;
    private long parkingLotId;
}
