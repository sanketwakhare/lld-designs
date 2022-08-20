package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Seat extends BaseModel {
    private String seatName;
    @ManyToOne
    private Auditorium auditorium;
    private SeatType seatType;
    private SeatStatus seatStatus;
    @OneToMany(mappedBy = "seat")
    private List<ShowSeat> showSeats;
}
