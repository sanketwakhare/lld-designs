package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisplayBoardRepository extends JpaRepository<DisplayBoard, Long> {

    @Query("select d from DisplayBoard d where d.displayBoardNumber= :displayBoardNumber")
    Optional<DisplayBoard> findByDisplayBoardNumber(String displayBoardNumber);
}
