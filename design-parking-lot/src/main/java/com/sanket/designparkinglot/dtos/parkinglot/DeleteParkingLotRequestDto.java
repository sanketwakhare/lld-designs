package com.sanket.designparkinglot.dtos.parkinglot;

import com.sanket.designparkinglot.dtos.base.request.DeleteRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteParkingLotRequestDto extends DeleteRequestDto {

    private long parkingLotId;
}
