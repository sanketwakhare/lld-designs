package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.models.gates.EntryGate;
import com.sanket.designparkinglot.models.gates.ExitGate;
import com.sanket.designparkinglot.models.gates.Gate;
import com.sanket.designparkinglot.models.gates.GateType;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.repositories.FloorRepository;
import com.sanket.designparkinglot.repositories.GateRepository;
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

    private final GateRepository gateRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, FloorRepository floorRepository, GateRepository gateRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.floorRepository = floorRepository;
        this.gateRepository = gateRepository;
    }

    public ParkingLot addParkingLot(String address, int numberOfFloors) {

        // create new parking lot and save
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        // set default parameters of baseModel
        setCreateModelDefaults(parkingLot);
        parkingLot = parkingLotRepository.save(parkingLot);

        // add floors
        for (int floorNumber = 0; floorNumber < numberOfFloors; floorNumber++) {
            Floor floor = new Floor();
            floor.setFloorNumber("F" + (floorNumber + 1));
            floor.setParkingLot(parkingLot);
            setCreateModelDefaults(floor);
            floorRepository.save(floor);
        }
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

    public ParkingLot assignGate(Long parkingLotId, Long gateId) throws NoParkingLotException, NoGateException {
        Optional<ParkingLot> dbParkingLot = parkingLotRepository.findById(parkingLotId);
        if (dbParkingLot.isEmpty()) {
            throw new NoParkingLotException(parkingLotId);
        }
        Optional<Gate> dbGate = gateRepository.findById(gateId);
        if (dbGate.isEmpty()) {
            throw new NoGateException(gateId);
        }

        ParkingLot parkingLot = dbParkingLot.get();
        Gate gate = dbGate.get();

        gate.setParkingLot(parkingLot);
        gateRepository.save(gate);
        return parkingLot;
    }
}
