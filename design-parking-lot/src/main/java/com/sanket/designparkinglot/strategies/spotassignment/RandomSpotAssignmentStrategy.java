package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.exceptions.NoSpotAvailableException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotStatus;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {
    @Override
    public SpotAssignmentStrategyType getStrategyType() {
        return SpotAssignmentStrategyType.RANDOM;
    }

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
                    spot.setSpotStatus(SpotStatus.OCCUPIED);
                    return spot;
                }
            }
        }
        // if no spot available
        throw new NoSpotAvailableException();
    }
}
