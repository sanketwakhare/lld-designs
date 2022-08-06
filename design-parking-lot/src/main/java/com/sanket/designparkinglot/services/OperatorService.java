package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorService extends BaseService {

    private final OperatorRepository operatorRepository;

    @Autowired
    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public Operator addOperator(String operatorName) {
        Operator operator = new Operator();
        operator.setName(operatorName);
        setCreateModelDefaults(operator);
        return operatorRepository.save(operator);
    }
}
