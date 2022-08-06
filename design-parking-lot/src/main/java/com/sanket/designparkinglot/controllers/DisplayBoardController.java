package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.CreateDisplayBoardResponseDto;
import com.sanket.designparkinglot.dtos.displayboard.GetDisplayBoardRequestDto;
import com.sanket.designparkinglot.dtos.displayboard.GetDisplayBoardResponseDto;
import com.sanket.designparkinglot.exceptions.EntityAlreadyExistsException;
import com.sanket.designparkinglot.exceptions.NoDisplayBoardException;
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

    public GetDisplayBoardResponseDto getDisplayBoard(GetDisplayBoardRequestDto getDisplayBoardRequestDto) {
        GetDisplayBoardResponseDto getDisplayBoardResponseDto = new GetDisplayBoardResponseDto();
        long displayBoardId = getDisplayBoardRequestDto.getDisplayBoardId();
        try {
            DisplayBoard displayBoard = displayBoardService.getDisplayBoard(displayBoardId);
            getDisplayBoardResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            getDisplayBoardResponseDto.setDisplayBoard(displayBoard);
        } catch (Exception | NoDisplayBoardException e) {
            getDisplayBoardResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            getDisplayBoardResponseDto.setMessage(e.getMessage());
        }
        return getDisplayBoardResponseDto;
    }
}
