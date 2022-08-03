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

    @Autowired
    public SpotService(SpotRepository spotRepository, FloorRepository floorRepository) {
        this.spotRepository = spotRepository;
    }

    public Spot addSpot(String spotNumber, SpotType spotType) {
        Spot spot = new Spot();
        spot.setSpotNumber(spotNumber);
        spot.setSpotType(spotType);
        spot.setSpotStatus(SpotStatus.AVAILABLE);
        setCreateModelDefaults(spot);

        return spotRepository.save(spot);
    }


}
