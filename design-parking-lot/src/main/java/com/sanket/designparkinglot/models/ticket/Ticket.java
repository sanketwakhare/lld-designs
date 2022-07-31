package com.sanket.designparkinglot.models.ticket;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.spot.Spot;
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
