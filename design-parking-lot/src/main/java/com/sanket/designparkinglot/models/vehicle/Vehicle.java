package com.sanket.designparkinglot.models.vehicle;

import com.sanket.designparkinglot.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle extends BaseModel {

    private String number;
    private VehicleType vehicleType;

    public Vehicle(String number, VehicleType vehicleType) {
        this.number = number;
        this.vehicleType = vehicleType;
    }

}
