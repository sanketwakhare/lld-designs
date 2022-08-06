package com.sanket.designparkinglot.models.floor;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Floor extends BaseModel {

    private String floorNumber;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private Set<Spot> spots;

    @ManyToOne(fetch = FetchType.EAGER)
    private ParkingLot parkingLot;

    public Floor(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Floor() {

    }
}
