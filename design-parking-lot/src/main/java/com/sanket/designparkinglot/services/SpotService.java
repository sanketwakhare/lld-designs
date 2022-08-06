package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoFloorException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotStatus;
import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotService extends BaseService {

    private final SpotRepository spotRepository;

    private final FloorRepository floorRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository, FloorRepository floorRepository) {
        this.spotRepository = spotRepository;
        this.floorRepository = floorRepository;
    }

    public Spot addSpot(String spotNumber, SpotType spotType, Long floorId) throws NoFloorException {

        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        Floor floor = dbFloor.get();

        // create new spot
        Spot spot = new Spot();
        spot.setSpotNumber(spotNumber);
        spot.setSpotType(spotType);
        spot.setSpotStatus(SpotStatus.AVAILABLE);
        setCreateModelDefaults(spot);
        spot = spotRepository.save(spot);

        // set floor and save spot
        spot.setFloor(floor);
        spot = spotRepository.save(spot);
        return spot;
    }


}
