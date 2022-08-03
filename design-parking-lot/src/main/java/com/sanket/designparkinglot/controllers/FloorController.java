package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.floor.CreateFloorRequestDto;
import com.sanket.designparkinglot.dtos.floor.CreateFloorResponseDto;
import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.models.floor.Floor;
import com.sanket.designparkinglot.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FloorController {

    private final FloorService floorService;

    @Autowired
    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    public CreateFloorResponseDto addFloor(CreateFloorRequestDto requestDto) {

        CreateFloorResponseDto responseDto = new CreateFloorResponseDto();

        // preprocessing
        Long parkingLotId = requestDto.getParkingLotId();
        String floorNumber = requestDto.getFloorNumber();

        // call service
        Floor floor;
        try {
            floor = floorService.addFloor(parkingLotId, floorNumber);

            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setFloor(floor);
            responseDto.setMessage("floor added successfully");
        } catch (Exception | NoParkingLotException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
