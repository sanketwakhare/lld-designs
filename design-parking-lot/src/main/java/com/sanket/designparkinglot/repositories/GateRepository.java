package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.gates.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {

    @Query("select g from Gate g where g.gateNumber=:gateNumber")
    Optional<Gate> findByGateNumber(String gateNumber);
}
