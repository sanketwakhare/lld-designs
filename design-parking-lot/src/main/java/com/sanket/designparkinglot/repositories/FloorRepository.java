package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.floor.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {
}
