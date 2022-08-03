package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.parkinglot.CreateParkingLotRequestDto;
import com.sanket.designparkinglot.dtos.parkinglot.CreateParkingLotResponseDto;
import com.sanket.designparkinglot.dtos.parkinglot.DeleteParkingLotRequestDto;
import com.sanket.designparkinglot.dtos.parkinglot.DeleteParkingLotResponseDto;
import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import com.sanket.designparkinglot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public CreateParkingLotResponseDto addParkingLot(CreateParkingLotRequestDto requestDto) {

        // preprocessing of request dto
        String address = requestDto.getAddress();
        int numberOfFloors = requestDto.getNumberOfFloors();

        // call service method
        ParkingLot parkingLot = parkingLotService.addParkingLot(address, numberOfFloors);

        // postprocessing of response entity into response dto
        CreateParkingLotResponseDto responseDto = new CreateParkingLotResponseDto();
        responseDto.setParkingLot(parkingLot);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("parking lot created successfully");
        return responseDto;
    }

    public DeleteParkingLotResponseDto deleteParkingLotById(DeleteParkingLotRequestDto requestDto) {

        DeleteParkingLotResponseDto responseDto = new DeleteParkingLotResponseDto();

        // preprocessing of request dto
        Long parkingLotId = requestDto.getParkingLotId();

        try {
            // call service
            parkingLotService.deleteParkingLotById(parkingLotId);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("parking lot deleted successfully");
        } catch (Exception | NoParkingLotException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
