package com.sanket.designparkinglot.dtos.floor;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.floor.Floor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFloorResponseDto extends ResponseDto {

    private Floor floor;
}
