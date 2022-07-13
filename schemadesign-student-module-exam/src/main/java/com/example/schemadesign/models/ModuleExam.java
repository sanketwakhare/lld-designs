package com.example.schemadesign.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ModuleExam extends BaseModel {

    private Date examDate;

    @ManyToOne
    private Module module;

    @ManyToOne
    private Exam exam;

}
