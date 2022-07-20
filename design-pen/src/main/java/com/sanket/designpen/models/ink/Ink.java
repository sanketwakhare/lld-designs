package com.sanket.designpen.models.ink;

import com.sanket.designpen.exceptions.NoColorDefinedException;
import com.sanket.designpen.exceptions.NoInkTypeDefinedException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Ink {

    private final InkColor color;
    private final double density;
    private final InkType inkType;
    private final boolean isErasable;

    private Ink(Builder builder) {
        this.inkType = builder.inkType;
        this.density = builder.density;
        this.isErasable = builder.isErasable;
        this.color = builder.color;
    }

    public static class Builder {

        private InkColor color;
        private double density;
        private InkType inkType;
        private boolean isErasable;

        public Builder setColor(InkColor color) {
            this.color = color;
            return this;
        }

        public Builder setDensity(double density) {
            this.density = density;
            return this;
        }

        public Builder setErasable(boolean erasable) {
            isErasable = erasable;
            return this;
        }

        public Builder setInkType(InkType inkType) {
            this.inkType = inkType;
            return this;
        }

        public Ink build() throws Exception {
            // validations
            if (Objects.isNull(inkType)) {
                throw new NoInkTypeDefinedException();
            }
            if (Objects.isNull(color)) {
                throw new NoColorDefinedException();
            }

            // create object
            return new Ink(this);
        }
    }

}
