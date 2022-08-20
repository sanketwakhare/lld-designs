package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Ticket extends BaseModel {
    @OneToMany(mappedBy = "ticket")
    List<ShowSeat> showSeats;

//    Show show;
//    List<Seat> seats;

    private double amount;
    private TicketStatus ticketStatus;
    @OneToOne
    private Payment payment;
}
