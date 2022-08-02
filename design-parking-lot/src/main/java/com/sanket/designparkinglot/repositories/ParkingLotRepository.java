package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}
