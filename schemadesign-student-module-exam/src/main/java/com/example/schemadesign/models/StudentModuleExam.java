package com.example.schemadesign.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StudentModuleExam extends BaseModel {
    private double marks;

    @ManyToOne
    private Student student;

    @ManyToOne
    private ModuleExam moduleExam;
}
