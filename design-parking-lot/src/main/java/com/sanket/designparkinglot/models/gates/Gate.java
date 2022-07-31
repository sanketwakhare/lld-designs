package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Gate extends BaseModel {

    private String gateNumber;
    private GateType gateType;
    private Operator operator;
    private GateStatus gateStatus;

    public Gate(GateType gateType, String gateNumber) {
        this.gateType = gateType;
    }
}
