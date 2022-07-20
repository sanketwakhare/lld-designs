package com.sanket.designpen.models.refills;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.tip.Tip;
import lombok.Data;

@Data
public class Refill {

    private Ink ink;
    private Tip tip;
    private RefillType refillType;

    Refill(RefillType refillType, Ink ink, Tip tip) {
        this.refillType = refillType;
        this.ink = ink;
        this.tip = tip;
    }

}
