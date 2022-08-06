package com.sanket.designparkinglot.dtos.gate;

import com.sanket.designparkinglot.dtos.base.request.UpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignOperatorRequestDto extends UpdateRequestDto {

    private long gateId;
    private long operatorId;
}
