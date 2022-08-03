package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.spot.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
}
