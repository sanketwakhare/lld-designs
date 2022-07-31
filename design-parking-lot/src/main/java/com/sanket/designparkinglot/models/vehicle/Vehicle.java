package com.sanket.designparkinglot.models.vehicle;

import com.sanket.designparkinglot.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Vehicle extends BaseModel {

    private String number;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle(String number, VehicleType vehicleType) {
        this.number = number;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {

    }
}
