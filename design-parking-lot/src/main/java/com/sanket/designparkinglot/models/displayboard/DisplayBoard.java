package com.sanket.designparkinglot.models.displayboard;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotStatus;
import com.sanket.designparkinglot.models.spot.SpotType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
public class DisplayBoard extends BaseModel {

    @Column(unique = true)
    private String displayBoardNumber;

    // EntryGate: owner entity (foreign key is generated at owner entity side)
    // DisplayBoard: mapped entity
    @OneToOne(mappedBy = "displayBoard", fetch = FetchType.EAGER)
    private EntryGate entryGate;

    public DisplayBoard() {

    }

    public Map<String, Map<SpotType, Integer>> getSpotAvailability(ParkingLot parkingLot) {
        Map<String, Map<SpotType, Integer>> spotAvailability = new HashMap<>();
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
}
