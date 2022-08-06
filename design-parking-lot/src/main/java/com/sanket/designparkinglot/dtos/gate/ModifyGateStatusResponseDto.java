package com.sanket.designparkinglot.dtos.gate;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.gates.Gate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyGateStatusResponseDto extends ResponseDto {

    private Gate gate;
}
