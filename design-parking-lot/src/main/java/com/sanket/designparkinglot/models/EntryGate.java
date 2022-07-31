package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryGate extends Gate {

    private DisplayBoard displayBoard;

    public EntryGate() {
        super(GateType.ENTRY);
    }

    public Ticket generateTicket() {
        // TODO: implement method
        return null;
    }
}
