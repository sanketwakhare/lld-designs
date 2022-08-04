package com.sanket.designparkinglot.models.spot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Spot extends BaseModel {

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Floor floor;

    public Spot() {
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    public Spot(SpotType spotType) {
        this();
        this.spotType = spotType;
    }

    public Spot(Floor floor, SpotType spotType, String spotNumber) {
        this(spotType);
        this.floor = floor;
        this.spotNumber = spotNumber;
        this.spotStatus = SpotStatus.AVAILABLE;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.getId(), ((Spot) obj.getClass().cast(obj)).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotType, floor, getId());
    }
}
