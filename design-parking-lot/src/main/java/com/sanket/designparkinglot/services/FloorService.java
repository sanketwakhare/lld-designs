package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoFloorException;
import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.exceptions.NoSpotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.ParkingLotRepository;
import com.sanket.designparkinglot.repositories.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FloorService extends BaseService {

    private final ParkingLotRepository parkingLotRepository;
    private final FloorRepository floorRepository;

    private final SpotRepository spotRepository;

    @Autowired
    public FloorService(ParkingLotRepository parkingLotRepository, FloorRepository floorRepository, SpotRepository spotRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.floorRepository = floorRepository;
        this.spotRepository = spotRepository;
    }

    public Floor addFloor(long parkingLotId, String floorNumber) throws NoParkingLotException {

        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        ParkingLot parkingLot = dbParkingLot.get();

        Floor floor = new Floor();
        setCreateModelDefaults(floor);
        floor.setFloorNumber(floorNumber);
        floor.setParkingLot(parkingLot);

        // save new floor
        return floorRepository.save(floor);
    }

    public void allocateSpot(long floorId, long spotId) throws NoFloorException, NoSpotException {
        // get Floor by floorId
        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        Optional<Spot> dbSpot = spotRepository.findById(spotId);
        if (dbSpot.isEmpty()) {
            throw new NoSpotException(spotId);
        }

        // allocate spot to floor and save floor
        Floor floor = dbFloor.get();
        Spot spot = dbSpot.get();
        if (spot.getFloor().equals(floor)) {
            // if current floor is already allocated to spot
            return;
        }
        spot.setFloor(floor);
        setUpdateModelDefaults(spot);
        spotRepository.save(spot);
    }

    public void deallocateSpot(long floorId, long spotId) throws NoFloorException, NoSpotException {
        // get Floor by floorId
        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        Optional<Spot> dbSpot = spotRepository.findById(spotId);
        if (dbSpot.isEmpty()) {
            throw new NoSpotException(spotId);
        }

        // deallocate floor from spot and save spot
        Spot spot = dbSpot.get();
        setUpdateModelDefaults(spot);
        spot.setFloor(null);
        spotRepository.save(spot);
    }

    public Floor getFloorById(long floorId) throws NoFloorException {
        // get Floor by floorId
        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        return dbFloor.get();
    }
}
