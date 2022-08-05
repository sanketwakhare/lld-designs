package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy {

    @Override
    public SpotAssignmentStrategyType getStrategyType() {
        return SpotAssignmentStrategyType.NEAREST_AVAILABLE;
    }

    @Override
    public Spot assignSpot(ParkingLot parkingLot, Vehicle vehicle, EntryGate gate) throws NoSpotAvailableException {
        // TODO
        return null;
    }
}
