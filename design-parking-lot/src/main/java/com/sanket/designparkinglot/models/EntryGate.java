package com.sanket.designparkinglot.models;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class EntryGate extends Gate {

    private DisplayBoard displayBoard;

    private SpotAssignmentStrategy spotAssignmentStrategy;

    public EntryGate(DisplayBoard displayBoard, SpotAssignmentStrategy spotAssignmentStrategy) {
        super(GateType.ENTRY);
        this.displayBoard = displayBoard;
    }

    public Ticket generateTicket(ParkingLot parkingLot, Vehicle vehicle) {
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
