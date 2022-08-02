package com.sanket.designparkinglot.models.operator;

import com.sanket.designparkinglot.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Operator extends BaseModel {

    private int operatorId;
    private String name;

    public Operator(String name) {
        this.name = name;
    }

    public Operator() {

    }
}
