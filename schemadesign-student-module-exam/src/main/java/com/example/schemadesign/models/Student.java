package com.example.schemadesign.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Student extends BaseModel {

    private String name;
    private String address;
    private String email;
    private String password;
    private String phoneNumber;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Module> enrolledModules;
}

