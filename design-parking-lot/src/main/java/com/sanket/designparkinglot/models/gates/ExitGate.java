package com.sanket.designparkinglot.models.gates;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ExitGate extends Gate {

    public ExitGate(String gateNumber) {
        super(GateType.EXIT, gateNumber);
    }

    public ExitGate() {
        super(GateType.EXIT);
    }

}
