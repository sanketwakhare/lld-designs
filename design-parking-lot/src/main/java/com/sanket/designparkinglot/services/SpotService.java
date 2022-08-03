package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoFloorException;
import com.sanket.designparkinglot.exceptions.NoSpotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotStatus;
import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public Spot addSpot(String spotNumber, SpotType spotType) {
        Spot spot = new Spot();
        spot.setSpotNumber(spotNumber);
        spot.setSpotType(spotType);
        spot.setSpotStatus(SpotStatus.AVAILABLE);
        setCreateModelDefaults(spot);

        return spotRepository.save(spot);
    }

    public void assignSpot(Long floorId, Long spotId) throws NoFloorException, NoSpotException {
        // get Floor by floorId
        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        Optional<Spot> dbSpot = spotRepository.findById(spotId);
        if (dbSpot.isEmpty()) {
            throw new NoSpotException(spotId);
        }

        // assign spot to floor and save floor
        Floor floor = dbFloor.get();
        List<Spot> existingSpots = floor.getSpots();
        if (Objects.isNull(existingSpots)) {
            existingSpots = new ArrayList<>();
        }
        existingSpots.add(dbSpot.get());
        floorRepository.save(floor);

        // assign floor to spot and save spot
        Spot spot = dbSpot.get();
        spot.setFloor(floor);
        spotRepository.save(spot);
    }
}
