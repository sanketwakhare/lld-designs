package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.gate.*;
import com.sanket.designparkinglot.exceptions.GateCreationException;
import com.sanket.designparkinglot.exceptions.InvalidEntryGateException;
import com.sanket.designparkinglot.exceptions.NoDisplayBoardException;
import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.models.gates.EntryGate;
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

    public ModifyGateStatusResponseDto modifyGateStatus(ModifyGateStatusRequestDto modifyGateStatusRequestDto) {
        ModifyGateStatusResponseDto modifyGateStatusResponseDto = new ModifyGateStatusResponseDto();
        // pre process request dto
        Long gateId = modifyGateStatusRequestDto.getGateId();
        GateStatus gateStatus = modifyGateStatusRequestDto.getGateStatus();
        try {
            // call service
            Gate gate = gateService.modifyGateStatus(gateId, gateStatus);
            modifyGateStatusResponseDto.setGate(gate);
            modifyGateStatusResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | NoGateException e) {
            modifyGateStatusResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            modifyGateStatusResponseDto.setMessage(e.getMessage());
        }
        return modifyGateStatusResponseDto;
    }

    public AssignDisplayBoardResponseDto assignDisplayBoard(AssignDisplayBoardRequestDto assignDisplayBoardRequestDto) {
        AssignDisplayBoardResponseDto assignDisplayBoardResponseDto = new AssignDisplayBoardResponseDto();
        Long gateId = assignDisplayBoardRequestDto.getGateId();
        Long displayBoardId = assignDisplayBoardRequestDto.getDisplayBoardId();

        try {
            // call to service method
            EntryGate entryGate = gateService.assignDisplayBoard(gateId, displayBoardId);

            assignDisplayBoardResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            assignDisplayBoardResponseDto.setEntryGate(entryGate);
        } catch (Exception | NoGateException | InvalidEntryGateException | NoDisplayBoardException e) {
            assignDisplayBoardResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            assignDisplayBoardResponseDto.setMessage(e.getMessage());
        }
        return assignDisplayBoardResponseDto;
    }

}
