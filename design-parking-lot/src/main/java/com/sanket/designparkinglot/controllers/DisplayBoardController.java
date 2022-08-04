package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardResponseDto;
import com.sanket.designparkinglot.exceptions.EntityAlreadyExistsException;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.services.DisplayBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DisplayBoardController {

    private final DisplayBoardService displayBoardService;

    @Autowired
    public DisplayBoardController(DisplayBoardService displayBoardService) {
        this.displayBoardService = displayBoardService;
    }

    public CreateDisplayBoardResponseDto addDisplayBoard(CreateDisplayBoardRequestDto createDisplayBoardRequestDto) {
        CreateDisplayBoardResponseDto createDisplayBoardResponseDto = new CreateDisplayBoardResponseDto();
        String displayBoardNumber = createDisplayBoardRequestDto.getDisplayBoardNumber();
        try {
            DisplayBoard displayBoard = displayBoardService.addDisplayBoard(displayBoardNumber);
            createDisplayBoardResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            createDisplayBoardResponseDto.setDisplayBoard(displayBoard);
        } catch (Exception | EntityAlreadyExistsException e) {
            createDisplayBoardResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createDisplayBoardResponseDto.setMessage(e.getMessage());
        }
        return createDisplayBoardResponseDto;
    }
}
