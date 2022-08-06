package com.sanket.designparkinglot.exceptions;

import com.sanket.designparkinglot.models.ticket.Ticket;

import java.util.Optional;

public class NoTicketException extends Throwable {

    public NoTicketException(long ticketId) {
        super("no ticket present with id " + ticketId);
    }
}
