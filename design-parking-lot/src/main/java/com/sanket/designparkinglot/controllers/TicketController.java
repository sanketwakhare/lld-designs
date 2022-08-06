package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.ticket.CreateTicketRequestDto;
import com.sanket.designparkinglot.dtos.ticket.CreateTicketResponseDto;
import com.sanket.designparkinglot.exceptions.*;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.services.TicketService;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public CreateTicketResponseDto createTicket(CreateTicketRequestDto createTicketRequestDto) {
        CreateTicketResponseDto createTicketResponseDto = new CreateTicketResponseDto();
        long parkingLotId = createTicketRequestDto.getParkingLotId();
        long entryGateId = createTicketRequestDto.getGateId();
        long vehicleId = createTicketRequestDto.getVehicleId();
        SpotAssignmentStrategyType spotAssignmentStrategyType = createTicketRequestDto.getSpotAssignmentStrategyType();

        try {
            Ticket ticket = ticketService.createTicket(parkingLotId, entryGateId, vehicleId, spotAssignmentStrategyType);
            createTicketResponseDto.setTicket(ticket);
            createTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | NoParkingLotException | NoGateException | InvalidEntryGateException |
                 NoVehiclePresentException | NoSpotAvailableException e) {
            createTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createTicketResponseDto.setMessage(e.getMessage());
        }
        return createTicketResponseDto;
    }
}
