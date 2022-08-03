package com.sanket.designparkinglot.models.spot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Spot extends BaseModel {

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Floor floor;

    public Spot(Floor floor, SpotType spotType, String spotNumber) {
        this.floor = floor;
        this.spotType = spotType;
        this.spotNumber = spotNumber;
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    public Spot() {

    }
}
