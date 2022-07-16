package com.sanket.netflixschema.models;

import com.sanket.netflixschema.enums.ProfileType;

import javax.persistence.Entity;

@Entity(name = "loginuser")
public class LoginUser extends BaseModel {
    private String email;
    private String password;

    public Profile createProfile(String name, ProfileType profileType) {
        // create object in db as well
        // simulation only
        return new Profile(name, profileType);
    }
}
