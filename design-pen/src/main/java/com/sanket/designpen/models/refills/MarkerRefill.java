package com.sanket.designpen.models.refills;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.tip.Tip;

public class MarkerRefill extends Refill{

    public MarkerRefill(Ink ink, Tip tip) {
        super(RefillType.MARKER, ink, tip);
    }
}
