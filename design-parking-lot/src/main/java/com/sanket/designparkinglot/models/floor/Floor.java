package com.sanket.designparkinglot.models.floor;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.spot.Spot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Floor extends BaseModel {

    private String floorNumber;

    @OneToMany(mappedBy = "floor")
    private List<Spot> spots;

    public Floor(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Floor() {

    }
}
