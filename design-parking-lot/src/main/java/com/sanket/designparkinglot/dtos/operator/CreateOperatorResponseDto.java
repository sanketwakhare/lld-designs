package com.sanket.designparkinglot.dtos.operator;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.operator.Operator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOperatorResponseDto extends ResponseDto {

    private Operator operator;
}
