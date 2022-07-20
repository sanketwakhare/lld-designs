package com.sanket.designpen.models.refills;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.tip.Tip;

public class BallRefill extends Refill {

    public BallRefill(Ink ink, Tip tip) {
        super(RefillType.BALL, ink, tip);
    }
}
