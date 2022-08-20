package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Auditorium extends BaseModel {
    private String name;
    @ManyToOne
    private Theatre theatre;
    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seats;
    @OneToMany(mappedBy = "auditorium")
    private List<Show> shows;
}
