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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Floor addFloor(Long parkingLotId, String floorNumber) throws NoParkingLotException {

        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        Floor floor = new Floor();
        setCreateModelDefaults(floor);
        floor.setFloorNumber(floorNumber);

        // save new floor
        floor = floorRepository.save(floor);

        // assign new floor to the parking lot
        ParkingLot parkingLot = dbParkingLot.get();
        parkingLot.getFloors().add(floor);
        parkingLotRepository.save(parkingLot);

        return floor;
    }

    public void allocateSpot(Long floorId, Long spotId) throws NoFloorException, NoSpotException {
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
        setUpdateModelDefaults(floor);
        List<Spot> existingSpots = floor.getSpots();
        if (Objects.isNull(existingSpots)) {
            existingSpots = new ArrayList<>();
        }
        existingSpots.add(dbSpot.get());
        floorRepository.save(floor);

        // add floor to spot and save spot
        Spot spot = dbSpot.get();
        setUpdateModelDefaults(spot);
        spot.setFloor(floor);
        spotRepository.save(spot);
    }

    public void deallocateSpot(Long floorId, Long spotId) throws NoFloorException, NoSpotException {
        // get Floor by floorId
        Optional<Floor> dbFloor = floorRepository.findById(floorId);
        if (dbFloor.isEmpty()) {
            throw new NoFloorException(floorId);
        }
        Optional<Spot> dbSpot = spotRepository.findById(spotId);
        if (dbSpot.isEmpty()) {
            throw new NoSpotException(spotId);
        }

        // deallocate spot to floor and save floor
        Floor floor = dbFloor.get();
        setUpdateModelDefaults(floor);
        List<Spot> existingSpots = floor.getSpots();
        if (!Objects.isNull(existingSpots)) {
            existingSpots.remove(dbSpot.get());
        }
        floorRepository.save(floor);

        // deallocate floor to spot and save spot
        Spot spot = dbSpot.get();
        setUpdateModelDefaults(spot);
        spot.setFloor(null);
        spotRepository.save(spot);
    }
}
