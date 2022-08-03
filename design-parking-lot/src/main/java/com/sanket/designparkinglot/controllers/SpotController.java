package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.spot.CreateSpotRequestDto;
import com.sanket.designparkinglot.dtos.spot.CreateSpotResponseDto;
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


}
