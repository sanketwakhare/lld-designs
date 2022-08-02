package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FloorService extends BaseService {

    private final ParkingLotRepository parkingLotRepository;
    private final FloorRepository floorRepository;

    @Autowired
    public FloorService(ParkingLotRepository parkingLotRepository, FloorRepository floorRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.floorRepository = floorRepository;
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
}
