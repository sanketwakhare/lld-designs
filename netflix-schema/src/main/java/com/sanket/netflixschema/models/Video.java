package com.sanket.netflixschema.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "video")
public class Video extends BaseModel {
    private String title;
    private String description;

    @ManyToMany
    private List<Actor> actors;

    @OneToMany(mappedBy = "video")
    private List<VideoProfile> videoProfiles;

}
