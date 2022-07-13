package com.example.schemadesign.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Module extends BaseModel {
    private String name;

    @ManyToMany
    private List<Student> enrolledStudents;

    @OneToMany(mappedBy = "module")
    List<ModuleExam> moduleExams;
}
