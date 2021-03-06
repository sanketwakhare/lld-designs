package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public abstract class Gate extends BaseModel {

    private String gateNumber;

    @Enumerated(EnumType.STRING)
    private GateType gateType;

    @OneToOne
    private Operator operator;

    @Enumerated(EnumType.STRING)
    private GateStatus gateStatus;

    public Gate(GateType gateType) {
        this.gateType = gateType;
    }

    public Gate(GateType gateType, String gateNumber) {
        this(gateType);
        this.gateNumber = gateNumber;
    }

    public Gate() {

    }
}
