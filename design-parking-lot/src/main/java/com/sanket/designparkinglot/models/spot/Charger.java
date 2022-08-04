package com.sanket.designparkinglot.models.spot;

import com.sanket.designparkinglot.models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Charger extends BaseModel {

    @OneToOne
    private ElectricSpot electricSpot;
}
