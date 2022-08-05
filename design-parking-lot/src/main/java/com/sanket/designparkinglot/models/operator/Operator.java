package com.sanket.designparkinglot.models.operator;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.gates.Gate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Operator extends BaseModel {

    private String name;

    @OneToOne(mappedBy = "operator", fetch = FetchType.EAGER)
    private Gate gate;

    public Operator() {

    }

    public Operator(String name) {
        this.name = name;
    }
}
