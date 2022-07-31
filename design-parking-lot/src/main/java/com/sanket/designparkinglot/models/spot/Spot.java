package com.sanket.designparkinglot.models.spot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spot extends BaseModel {

    private String spotNumber;
    private SpotType spotType;
    private SpotStatus spotStatus;
    private Floor floor;

    public Spot(Floor floor, SpotType spotType, String spotNumber) {
        this.floor = floor;
        this.spotType = spotType;
        this.spotNumber = spotNumber;
    }
}
