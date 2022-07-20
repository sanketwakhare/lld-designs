package com.sanket.designpen.models.pens;

import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.strategies.DefaultWriteBehavior;
import com.sanket.designpen.strategies.WriteBehaviour;
import lombok.Data;

@Data
public abstract class Pen {
    private String name;
    private String brand;
    private double price;
    private PenType penType;
    private WriteBehaviour writeBehaviour;

    public Pen() {
        this.writeBehaviour = new DefaultWriteBehavior();
    }

    public Pen(PenType penType) {
        this();
        this.penType = penType;
    }

    public Pen(PenType penType, WriteBehaviour writeBehaviour) {
        this();
        this.penType = penType;
        this.writeBehaviour = writeBehaviour;
    }

    public void write() {
        this.writeBehaviour.write();
    }

    public abstract InkColor getColor();
}
