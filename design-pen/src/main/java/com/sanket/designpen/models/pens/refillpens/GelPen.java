package com.sanket.designpen.models.pens.refillpens;

import com.sanket.designpen.exceptions.NoRefillPresentException;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.pens.Pen;
import com.sanket.designpen.models.pens.PenType;
import com.sanket.designpen.models.refills.Refill;
import com.sanket.designpen.strategies.WriteBehaviour;
import lombok.Getter;

import java.util.Objects;

@Getter
public class GelPen extends Pen implements RefillPen {

    private Refill refill;
    private final boolean canChangeRefill;

    private GelPen(Builder builder) {
        super(PenType.GEL);
        this.refill = builder.refill;
        this.canChangeRefill = builder.canChangeRefill;
        this.setWriteBehaviour(builder.writeBehaviour);
        this.setName(builder.name);
        this.setBrand(builder.brand);
        this.setPrice(builder.price);
    }

    public static class Builder {

        private String name;
        private String brand;
        private double price;

        private Refill refill;
        private boolean canChangeRefill;
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

        public Builder setRefill(Refill refill) {
            this.refill = refill;
            return this;
        }

        public Builder setCanChangeRefill(boolean canChangeRefill) {
            this.canChangeRefill = canChangeRefill;
            return this;
        }

        public Builder setWriteBehaviour(WriteBehaviour writeBehaviour) {
            this.writeBehaviour = writeBehaviour;
            return this;
        }

        public GelPen build() throws Exception {
            if (Objects.isNull(refill)) {
                throw new NoRefillPresentException();
            }
            if (Objects.isNull(name) || name.isEmpty()) {
                throw new Exception("pen name cannot be empty");
            }

            return new GelPen(this);
        }
    }

    @Override
    public InkColor getColor() {
        return getReFill().getInk().getColor();
    }

    @Override
    public Refill getReFill() {
        return this.refill;
    }

    @Override
    public boolean canChangeRefill() {
        return this.canChangeRefill;
    }

    public void changeRefill(Refill newRefill) {
        refill = newRefill;
    }

}
