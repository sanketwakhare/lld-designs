package com.sanket.netflixschema.models;

import com.sanket.netflixschema.enums.ProfileType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "profile")
public class Profile extends BaseModel {

    private String name;
    private ProfileType type;

    @ManyToOne
    private LoginUser user;

    @OneToMany(mappedBy = "profile")
    private List<VideoProfile> videos;
}
