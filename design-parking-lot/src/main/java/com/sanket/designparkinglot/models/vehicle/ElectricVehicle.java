package com.sanket.designparkinglot.models.vehicle;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
public class ElectricVehicle extends Vehicle {

    @OneToOne
    private Charger charger;
}
