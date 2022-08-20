package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;
    @ManyToOne
    private City city;
    @OneToMany(mappedBy = "theatre")
    private List<Auditorium> auditoriums;
    @OneToMany(mappedBy = "theatre")
    private List<Show> shows;
}
