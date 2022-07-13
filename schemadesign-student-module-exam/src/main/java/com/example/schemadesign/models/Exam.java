package com.example.schemadesign.models;

import javax.persistence.Entity;

@Entity
public class Exam extends BaseModel {

    private String name;

    private double duration;

}
