package com.sanket.designpen.models.refills;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.tip.Tip;

public class GelRefill extends Refill {

    public GelRefill(Ink ink, Tip tip) {
        super(RefillType.GEL, ink, tip);
    }
}
