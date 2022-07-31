package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.EntryGate;
import com.sanket.designparkinglot.models.ParkingLot;
import com.sanket.designparkinglot.models.Spot;
import com.sanket.designparkinglot.models.Vehicle;

public interface SpotAssignmentStrategy {

    Spot assignSpot(ParkingLot parkingLot, Vehicle vehicle, EntryGate gate) throws NoSpotAvailableException;
}
