package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select v from Vehicle v where v.number = :vehicleNumber")
    Optional<Vehicle> findByNumber(String vehicleNumber);

}
