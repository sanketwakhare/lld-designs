package com.sanket.designpen.models.pens;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.pens.refillpens.BallPen;
import com.sanket.designpen.models.refills.Refill;
import com.sanket.designpen.models.tip.Tip;
import com.sanket.designpen.strategies.WriteBehaviour;
import lombok.Getter;

@Getter
public class FountainPen extends Pen {

    private Ink ink;
    private Tip tip;
    private final boolean canFillInk;

    private FountainPen(Builder builder) {
        super(PenType.FOUNTAIN);
        this.canFillInk = builder.canFillInk;
        this.setWriteBehaviour(builder.writeBehaviour);
        this.setName(builder.name);
        this.setBrand(builder.brand);
        this.setPrice(builder.price);
        this.ink = builder.ink;
        this.tip = builder.tip;
    }

    public static class Builder {
        private String name;
        private String brand;
        private double price;
        private Ink ink;
        private Tip tip;
        private boolean canFillInk;
        private WriteBehaviour writeBehaviour;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setCanFillInk(boolean canFillInk) {
            this.canFillInk = canFillInk;
            return this;
        }

        public Builder setWriteBehaviour(WriteBehaviour writeBehaviour) {
            this.writeBehaviour = writeBehaviour;
            return this;
        }

        public Builder setInk(Ink ink) {
            this.ink = ink;
            return this;
        }

        public Builder setTip(Tip tip) {
            this.tip = tip;
            return this;
        }

        public FountainPen build() {
            // validations

            // object creation
            return new FountainPen(this);
        }
    }

    public void fillInk(Ink ink) {
        this.ink = ink;
    }

    @Override
    public void write() {
        this.getWriteBehaviour().write();
    }

    @Override
    public InkColor getColor() {
        return this.getInk().getColor();
    }

}
