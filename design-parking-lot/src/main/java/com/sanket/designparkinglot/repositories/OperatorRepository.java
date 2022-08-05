package com.sanket.designparkinglot.repositories;

import com.sanket.designparkinglot.models.operator.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

}
