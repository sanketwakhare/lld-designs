package com.sanket.designparkinglot.models.parkinglot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class ParkingLot extends BaseModel {

    private String address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Floor> floors;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private Set<EntryGate> entryGates;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private Set<ExitGate> exitGates;

}
