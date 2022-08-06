package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.floor.*;
import com.sanket.designparkinglot.exceptions.NoFloorException;
import com.sanket.designparkinglot.exceptions.NoParkingLotException;
import com.sanket.designparkinglot.exceptions.NoSpotException;
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
        long parkingLotId = requestDto.getParkingLotId();
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

    public AllocateSpotResponseDto allocateSpot(AllocateSpotRequestDto allocateSpotRequestDto) {

        AllocateSpotResponseDto allocateSpotResponseDto = new AllocateSpotResponseDto();
        long floorId = allocateSpotRequestDto.getFloorId();
        long spotId = allocateSpotRequestDto.getSpotId();

        try {
            floorService.allocateSpot(floorId, spotId);
            allocateSpotResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            allocateSpotResponseDto.setMessage("spot allocated successfully");
        } catch (Exception | NoFloorException | NoSpotException e) {
            allocateSpotResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            allocateSpotResponseDto.setMessage(e.getMessage());
        }

        return allocateSpotResponseDto;
    }

    public DeAllocateSpotResponseDto deallocateSpot(DeAllocateSpotRequestDto deAllocateSpotRequestDto) {

        DeAllocateSpotResponseDto deAllocateSpotResponseDto = new DeAllocateSpotResponseDto();
        long floorId = deAllocateSpotRequestDto.getFloorId();
        long spotId = deAllocateSpotRequestDto.getSpotId();

        try {
            floorService.deallocateSpot(floorId, spotId);
            deAllocateSpotResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            deAllocateSpotResponseDto.setMessage("spot deallocated successfully");
        } catch (Exception | NoFloorException | NoSpotException e) {
            deAllocateSpotResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            deAllocateSpotResponseDto.setMessage(e.getMessage());
        }

        return deAllocateSpotResponseDto;
    }

    public GetFloorResponseDto getFloorById(GetFloorRequestDto getFloorRequestDto) {
        GetFloorResponseDto getFloorResponseDto = new GetFloorResponseDto();
        long floorId = getFloorRequestDto.getFloorId();

        try {
            Floor floor = floorService.getFloorById(floorId);
            getFloorResponseDto.setFloor(floor);
            getFloorResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | NoFloorException e) {
            getFloorResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            getFloorResponseDto.setMessage(e.getMessage());
        }
        return getFloorResponseDto;
    }
}
