package com.sanket.designpen.models.tip;

import lombok.Data;

@Data
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Tip {

    private double radius;
    private TipType tipType;

    public Tip(double radius, TipType tipType) {
        this.radius = radius;
        this.tipType = tipType;
    }
}
