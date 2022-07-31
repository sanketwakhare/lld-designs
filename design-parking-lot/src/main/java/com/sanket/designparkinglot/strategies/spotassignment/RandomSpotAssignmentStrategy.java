package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.*;

import java.util.List;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {
    @Override
    public Spot assignSpot(ParkingLot parkingLot, Vehicle vehicle, EntryGate gate)
            throws NoSpotAvailableException {

        List<Floor> floors = parkingLot.getFloors();
        for (Floor floor : floors) {
            List<Spot> spots = floor.getSpots();
            for (Spot spot : spots) {
                if (SpotStatus.AVAILABLE.equals(spot.getSpotStatus())
                        && vehicle.getVehicleType().toString().equals(spot.getSpotType().toString())) {
                    // return available spot
                    spot.setSpotStatus(SpotStatus.AVAILABLE);
                    return spot;
                }
            }
        }
        // if no spot available
        throw new NoSpotAvailableException();
    }
}
