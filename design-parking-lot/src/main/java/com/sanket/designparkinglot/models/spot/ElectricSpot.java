package com.sanket.designparkinglot.models.spot;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class ElectricSpot extends Spot {

    @OneToOne(mappedBy = "electricSpot")
    private Charger charger;

    public ElectricSpot() {
        super(SpotType.ELECTRIC);
    }
}
