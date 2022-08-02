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

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {

    @ManyToOne
    private Vehicle vehicle;

    @OneToOne
    private Spot spot;

    @ManyToOne
    private EntryGate entryGate;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Floor floor;

    @ManyToOne
    private ParkingLot parkingLot;

    private Date entryTime;
}
