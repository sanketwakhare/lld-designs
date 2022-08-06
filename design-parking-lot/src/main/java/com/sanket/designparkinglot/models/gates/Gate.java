package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.parkinglot.ParkingLot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public abstract class Gate extends BaseModel {

    private String gateNumber;

    @Enumerated(EnumType.STRING)
    private GateType gateType;

    // Gate: owner entity
    // Operator: mapped entity
    @OneToOne(fetch = FetchType.EAGER)
    private Operator operator;

    @Enumerated(EnumType.STRING)
    private GateStatus gateStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private ParkingLot parkingLot;

    public Gate(GateType gateType) {
        this.gateType = gateType;
    }

    public Gate(GateType gateType, String gateNumber) {
        this(gateType);
        this.gateNumber = gateNumber;
    }

    public Gate() {

    }

    @Override
    public String toString() {
        return "Gate{" +
                "gateNumber='" + gateNumber + '\'' +
                ", gateType=" + gateType +
                ", operator=" + operator +
                ", gateStatus=" + gateStatus +
                ", parkingLot=" + parkingLot +
                '}';
    }
}
