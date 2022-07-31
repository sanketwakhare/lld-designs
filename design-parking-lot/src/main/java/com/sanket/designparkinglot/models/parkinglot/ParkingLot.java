package com.sanket.designparkinglot.models.parkinglot;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingLot extends BaseModel {

    private String address;
    private List<Floor> floors;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;

}
