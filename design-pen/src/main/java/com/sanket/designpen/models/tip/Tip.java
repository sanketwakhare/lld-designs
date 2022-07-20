package com.sanket.designpen.models.tip;

import com.sanket.designpen.models.ink.TipType;
import lombok.Data;

@Data
public class Tip {

    private double radius;
    private TipType tipType;

    public Tip(double radius, TipType tipType) {
        this.radius = radius;
        this.tipType = tipType;
    }
}
