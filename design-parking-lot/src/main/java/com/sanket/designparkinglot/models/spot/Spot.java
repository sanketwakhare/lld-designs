package com.sanket.designparkinglot.models.spot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Spot extends BaseModel {

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;

    @ManyToOne
    private Floor floor;

    public Spot(Floor floor, SpotType spotType, String spotNumber) {
        this.floor = floor;
        this.spotType = spotType;
        this.spotNumber = spotNumber;
    }

    public Spot() {

    }
}
