package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorRequestDto;
import com.sanket.designparkinglot.dtos.operator.CreateOperatorResponseDto;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OperatorController {

    private final OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public CreateOperatorResponseDto addOperator(CreateOperatorRequestDto createOperatorRequestDto) {
        CreateOperatorResponseDto createOperatorResponseDto = new CreateOperatorResponseDto();
        // pre process request dto
        String operatorName = createOperatorRequestDto.getName();
        try {
            // call service
            Operator operator = operatorService.addOperator(operatorName);
            createOperatorResponseDto.setOperator(operator);
            createOperatorResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            createOperatorResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createOperatorResponseDto.setMessage(e.getMessage());
        }
        return createOperatorResponseDto;
    }
}
