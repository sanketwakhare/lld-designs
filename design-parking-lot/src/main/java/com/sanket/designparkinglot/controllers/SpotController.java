package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.spot.AssignSpotRequestDto;
import com.sanket.designparkinglot.dtos.spot.AssignSpotResponseDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotRequestDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotResponseDto;
import com.sanket.designparkinglot.exceptions.NoFloorException;
import com.sanket.designparkinglot.exceptions.NoSpotException;
import com.sanket.designparkinglot.models.spot.Spot;
import com.sanket.designparkinglot.models.spot.SpotType;
import com.sanket.designparkinglot.services.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SpotController {

    private final SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    public CreateSpotResponseDto addSpot(CreateSpotRequestDto createSpotRequestDto) {

        CreateSpotResponseDto createSpotResponseDto = new CreateSpotResponseDto();
        String spotNumber = createSpotRequestDto.getSpotNumber();
        SpotType spotType = createSpotRequestDto.getSpotType();

        try {
            Spot spot = spotService.addSpot(spotNumber, spotType);
            createSpotResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            createSpotResponseDto.setSpot(spot);
        } catch (Exception e) {
            createSpotResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createSpotResponseDto.setMessage(e.getMessage());
        }

        return createSpotResponseDto;
    }

    public AssignSpotResponseDto assignSpot(AssignSpotRequestDto assignSpotRequestDto) {

        AssignSpotResponseDto assignSpotResponseDto = new AssignSpotResponseDto();
        Long floorId = assignSpotRequestDto.getFloorId();
        Long spotId = assignSpotRequestDto.getSpotId();

        try {
            spotService.assignSpot(floorId, spotId);
            assignSpotResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            assignSpotResponseDto.setMessage("spot assigned successfully");
        } catch (Exception | NoFloorException | NoSpotException e) {
            assignSpotResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            assignSpotResponseDto.setMessage(e.getMessage());
        }

        return assignSpotResponseDto;
    }
}
