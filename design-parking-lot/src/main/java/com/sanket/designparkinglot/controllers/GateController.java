package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.gate.CreateGateRequestDto;
import com.sanket.designparkinglot.dtos.gate.CreateGateResponseDto;
import com.sanket.designparkinglot.exceptions.GateCreationException;
import com.sanket.designparkinglot.models.gates.Gate;
import com.sanket.designparkinglot.models.gates.GateStatus;
import com.sanket.designparkinglot.models.gates.GateType;
import com.sanket.designparkinglot.services.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GateController {

    private final GateService gateService;

    @Autowired
    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    public CreateGateResponseDto addGate(CreateGateRequestDto createGateRequestDto) {
        CreateGateResponseDto createGateResponseDto = new CreateGateResponseDto();
        // pre process request dto
        String gateNumber = createGateRequestDto.getGateNumber();
        GateType gateType = createGateRequestDto.getGateType();
        GateStatus gateStatus = createGateRequestDto.getGateStatus();
        try {
            // call service
            Gate gate = gateService.addGate(gateNumber, gateType, gateStatus);
            createGateResponseDto.setGate(gate);
            createGateResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | GateCreationException e) {
            createGateResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createGateResponseDto.setMessage(e.getMessage());
        }
        return createGateResponseDto;
    }
}
