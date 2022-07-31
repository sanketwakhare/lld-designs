package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Gate extends BaseModel {

    private String gateNumber;
    private GateType gateType;
    private Operator operator;
    private GateStatus gateStatus;

    public Gate(GateType gateType) {
        this.gateType = gateType;
    }
}
