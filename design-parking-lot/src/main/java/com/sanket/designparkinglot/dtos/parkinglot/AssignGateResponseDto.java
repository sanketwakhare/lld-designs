package com.sanket.designparkinglot.dtos.parkinglot;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignGateResponseDto extends ResponseDto {
    private ParkingLot parkingLot;
}
