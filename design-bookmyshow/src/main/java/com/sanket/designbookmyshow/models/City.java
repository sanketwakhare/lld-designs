package com.sanket.designbookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class City extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
}
