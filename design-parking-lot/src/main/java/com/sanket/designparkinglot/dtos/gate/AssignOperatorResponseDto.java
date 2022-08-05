package com.sanket.designparkinglot.dtos.gate;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.Gate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignOperatorResponseDto extends ResponseDto {
    private Gate gate;
}
