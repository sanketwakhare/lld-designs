package com.sanket.designpen.models.pens.refillpens;

import com.sanket.designpen.models.refills.Refill;

public interface RefillPen {

    Refill getReFill();

    boolean canChangeRefill();

    void changeRefill(Refill refill);

}
