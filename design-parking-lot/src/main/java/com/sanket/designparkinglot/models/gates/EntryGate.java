package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Calendar;

@Getter
@Setter
@Entity
public class EntryGate extends Gate {

    @OneToOne
    private DisplayBoard displayBoard;

    public EntryGate(DisplayBoard displayBoard,
                     String gateNumber) {
        super(GateType.ENTRY);
        this.displayBoard = displayBoard;
    }

    public EntryGate() {
        super(GateType.ENTRY);
    }

    public Ticket generateTicket(ParkingLot parkingLot,
                                 Vehicle vehicle,
                                 SpotAssignmentStrategy spotAssignmentStrategy) {
        // TODO: verify working of this method
        Ticket ticket = new Ticket();

        ticket.setVehicle(vehicle);
        ticket.setEntryGate(this);
        ticket.setOperator(this.getOperator());
        ticket.setEntryTime(Calendar.getInstance().getTime());

        // assign spot
        Spot spot;
        try {
            spot = spotAssignmentStrategy.assignSpot(parkingLot, vehicle, this);
            ticket.setSpot(spot);
        } catch (NoSpotAvailableException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return ticket;
    }

    public void displayBoard() {
        System.out.println(displayBoard);
    }
}
