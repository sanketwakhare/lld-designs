package com.sanket.designparkinglot.models.parkinglot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.Gate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class ParkingLot extends BaseModel {

    private String address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLot")
    private Set<Floor> floors;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLot")
    private Set<Gate> gates;

}
