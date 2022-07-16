package com.sanket.netflixschema.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "actor")
public class Actor extends BaseModel {
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }
}
