package com.sanket.netflixschema.models;

import javax.persistence.Entity;

@Entity(name = "loginuser")
public class LoginUser extends BaseModel {
    private String email;
    private String password;
}
