package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.EntityAlreadyExistsException;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.models.vehicle.VehicleType;
import com.sanket.designparkinglot.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService extends BaseService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle registerVehicle(String vehicleNumber, VehicleType vehicleType) throws EntityAlreadyExistsException {

        Optional<Vehicle> dbVehicle = vehicleRepository.findByNumber(vehicleNumber);
        if (dbVehicle.isPresent()) {
            throw new EntityAlreadyExistsException(vehicleNumber);
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        setCreateModelDefaults(vehicle);
        return vehicleRepository.save(vehicle);
    }
}
