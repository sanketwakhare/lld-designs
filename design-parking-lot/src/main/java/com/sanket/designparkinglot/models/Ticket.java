package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ticket extends BaseModel {

    private Vehicle vehicle;
    private Spot spot;
    private EntryGate entryGate;
    private Operator operator;
    private Floor floor;
    private ParkingLot parkingLot;
    private Date entryTime;
}
