package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Actor extends BaseModel {
    private String name;
    @ManyToMany
    List<Movie> movies;
}
