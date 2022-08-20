package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Theatre theatre;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
    private ShowStatus showStatus;
}
