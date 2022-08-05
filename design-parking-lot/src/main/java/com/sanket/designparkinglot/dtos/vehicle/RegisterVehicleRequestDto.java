package com.sanket.designparkinglot.dtos.vehicle;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.models.vehicle.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterVehicleRequestDto extends CreateRequestDto {
    private String vehicleNumber;
    private VehicleType vehicleType;
}
