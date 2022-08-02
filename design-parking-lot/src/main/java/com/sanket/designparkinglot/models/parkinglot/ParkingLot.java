package com.sanket.designparkinglot.models.parkinglot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class ParkingLot extends BaseModel {

    private String address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Floor> floors;

    @OneToMany
    private List<EntryGate> entryGates;

    @OneToMany
    private List<ExitGate> exitGates;

    @OneToMany(mappedBy = "parkingLot")
    private List<DisplayBoard> displayBoards;

}
