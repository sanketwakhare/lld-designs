package com.sanket.designparkinglot.models.bill;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.operator.Operator;
import com.sanket.designparkinglot.models.ticket.Ticket;
import com.sanket.designparkinglot.models.gates.ExitGate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Bill extends BaseModel {

    private Date exitTime;

    @ManyToOne
    private ExitGate exitGate;

    private double charges;

    @OneToOne
    private Ticket ticket;

    @ManyToOne
    private Operator operator;

    @Enumerated(EnumType.STRING)
    private BillPaymentStatus billPaymentStatus;

}
