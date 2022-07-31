package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.vehicle.Vehicle;

public interface SpotAssignmentStrategy {

    Spot assignSpot(ParkingLot parkingLot, Vehicle vehicle, EntryGate gate) throws NoSpotAvailableException;
}
