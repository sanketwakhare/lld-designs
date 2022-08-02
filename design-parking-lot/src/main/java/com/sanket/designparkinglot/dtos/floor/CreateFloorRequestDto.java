package com.sanket.designparkinglot.dtos.floor;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFloorRequestDto extends CreateRequestDto {

    private Long parkingLotId;
    private String floorNumber;
}
