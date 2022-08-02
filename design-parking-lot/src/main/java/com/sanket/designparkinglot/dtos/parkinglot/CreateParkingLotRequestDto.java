package com.sanket.designparkinglot.dtos.parkinglot;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateParkingLotRequestDto extends CreateRequestDto {

    private String address;
    private int numberOfFloors;
}
