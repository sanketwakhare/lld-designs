package com.sanket.designparkinglot.dtos.gate;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.models.gates.GateStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyGateStatusRequestDto extends CreateRequestDto {

    private long gateId;
    private GateStatus gateStatus;
}
