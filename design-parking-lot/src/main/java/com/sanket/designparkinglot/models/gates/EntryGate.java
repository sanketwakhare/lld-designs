package com.sanket.designparkinglot.models.gates;

import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class EntryGate extends Gate {

    @OneToOne(fetch = FetchType.EAGER)
    private DisplayBoard displayBoard;

    public EntryGate() {
        super(GateType.ENTRY);
    }

    public EntryGate(DisplayBoard displayBoard,
                     String gateNumber) {
        this();
        this.displayBoard = displayBoard;
    }

}
