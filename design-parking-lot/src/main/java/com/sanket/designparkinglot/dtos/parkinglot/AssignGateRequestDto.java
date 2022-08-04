package com.sanket.designparkinglot.dtos.parkinglot;

import com.sanket.designparkinglot.dtos.base.request.UpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignGateRequestDto extends UpdateRequestDto {
    private Long parkingLotId;
    private Long gateId;
}
