package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    private ShowSeatStatus showSeatStatus;
    private double price;
    @ManyToOne
    private Ticket ticket;
}
