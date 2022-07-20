package com.sanket.designpen.models.pens;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.tip.Tip;
import com.sanket.designpen.strategies.WriteBehaviour;
import lombok.Getter;

@Getter
public class FountainPen extends Pen {

    private Ink ink;
    private Tip tip;
    private boolean canFillInk;

    public FountainPen(WriteBehaviour writeBehaviour) {
        super(PenType.FOUNTAIN, writeBehaviour);
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
