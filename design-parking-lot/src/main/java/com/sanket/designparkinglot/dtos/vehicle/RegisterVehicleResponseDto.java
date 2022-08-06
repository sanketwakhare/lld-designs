package com.sanket.designparkinglot.dtos.vehicle;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterVehicleResponseDto extends ResponseDto {

    private Vehicle vehicle;
}
