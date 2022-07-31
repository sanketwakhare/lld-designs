package com.sanket.designparkinglot.models;

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
