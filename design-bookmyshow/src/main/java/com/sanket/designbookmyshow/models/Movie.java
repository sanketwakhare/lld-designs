package com.sanket.designbookmyshow.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie extends BaseModel {
    private String name;
    private double duration;
//    List<Language> languages;
    @ManyToMany(mappedBy = "movies")
    List<Actor> cast;
    @OneToMany(mappedBy = "movie")
    private List<Show> shows;
    double rating;
}
