package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService extends BaseService {

    private final ParkingLotRepository parkingLotRepository;

    private final FloorRepository floorRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, FloorRepository floorRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.floorRepository = floorRepository;
    }

    public ParkingLot addParkingLot(String address, int numberOfFloors) {

        // create new parking lot and save
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        // set default parameters of baseModel
        setCreateModelDefaults(parkingLot);
        parkingLot = parkingLotRepository.save(parkingLot);

        // add floors
        List<Floor> floors = new ArrayList<>();
        for (int floorNumber = 0; floorNumber < numberOfFloors; floorNumber++) {
            Floor floor = new Floor();
            floor.setFloorNumber("F" + (floorNumber + 1));
            setCreateModelDefaults(floor);
            floor = floorRepository.save(floor);
            floors.add(floor);
        }
        parkingLot.setFloors(floors);

        // save parking lot
        return parkingLotRepository.save(parkingLot);
    }

    public void deleteParkingLotById(Long parkingLotId) throws NoParkingLotException {
        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        parkingLotRepository.deleteById(parkingLotId);
    }
}
