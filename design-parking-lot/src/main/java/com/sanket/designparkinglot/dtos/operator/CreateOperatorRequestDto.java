package com.sanket.designparkinglot.dtos.operator;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOperatorRequestDto extends CreateRequestDto {

    private String name;
}
