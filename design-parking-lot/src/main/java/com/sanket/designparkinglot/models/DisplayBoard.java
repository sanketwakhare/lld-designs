package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DisplayBoard extends BaseModel {

    private ParkingLot parkingLot;
    private Map<String, Map<SpotType, Integer>> spotAvailability;

    DisplayBoard(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        spotAvailability = new HashMap<>();
    }

    public Map<String, Map<SpotType, Integer>> getSpotAvailability() {
        for (Floor floor : parkingLot.getFloors()) {
            Map<SpotType, Integer> floorMap = new HashMap<>();
            for (Spot spot : floor.getSpots()) {
                SpotType spotType = spot.getSpotType();
                SpotStatus spotStatus = spot.getSpotStatus();
                int count = floorMap.getOrDefault(spotType, 0);
                count += spotStatus.equals(SpotStatus.AVAILABLE) ? 1 : 0;
                floorMap.put(spotType, count);
            }
            spotAvailability.put(floor.getFloorNumber(), floorMap);
        }
        return spotAvailability;
    }

    @Override
    public String toString() {
        return "DisplayBoard{" +
                "parkingLot=" + parkingLot +
                ", spotAvailability=" + spotAvailability +
                '}';
    }
}
