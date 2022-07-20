package com.sanket.designpen.factories;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.pens.FountainPen;
import com.sanket.designpen.models.tip.Tip;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.tip.TipType;
import com.sanket.designpen.models.pens.Pen;
import com.sanket.designpen.models.pens.refillpens.GelPen;
import com.sanket.designpen.models.pens.refillpens.MarkerPen;
import com.sanket.designpen.models.refills.GelRefill;
import com.sanket.designpen.models.refills.MarkerRefill;
import com.sanket.designpen.strategies.FastWriteBehavior;
import com.sanket.designpen.strategies.SharpWriteBehavior;
import com.sanket.designpen.strategies.SmoothWriteBehavior;

public class PenFactory {

    public static Pen getReynoldsRacerGelPen(InkColor color) throws Exception {
        Ink ink = InkFactory.getGelInk(color);
        Tip tip = new Tip(0.5, TipType.BALL);
        GelRefill gelRefill = new GelRefill(ink, tip);
        return new GelPen.Builder()
                .setRefill(gelRefill)
                .setCanChangeRefill(true)
                .setWriteBehaviour(new FastWriteBehavior())
                .setName("racer gel")
                .setBrand("reynolds")
                .setPrice(10)
                .build();
    }

    public static Pen getCamlinWhiteBoardMarker(InkColor color) throws Exception {
        Ink ink = InkFactory.getWhiteBoardMarkerInk(color);
        Tip tip = new Tip(5, TipType.SPONGE);
        MarkerRefill markerRefill = new MarkerRefill(ink, tip);
        return new MarkerPen.Builder()
                .setRefill(markerRefill)
                .setCanChangeRefill(true)
                .setWriteBehaviour(new SmoothWriteBehavior())
                .setBrand("camlin")
                .setName("white board marker")
                .setPrice(20)
                .build();
    }

    public static Pen getCamlinPermanentMarker(InkColor color) throws Exception {
        Ink ink = InkFactory.getPermanentMarkerInk(color);
        Tip tip = new Tip(5, TipType.SPONGE);
        MarkerRefill markerRefill = new MarkerRefill(ink, tip);
        return new MarkerPen.Builder()
                .setRefill(markerRefill)
                .setCanChangeRefill(true)
                .setWriteBehaviour(new SharpWriteBehavior())
                .setBrand("camlin")
                .setName("permanent marker")
                .setPrice(30)
                .build();
    }

    public static Pen getReynoldsFludoProFountainPen(InkColor color) throws Exception {
        Ink ink = InkFactory.getFountainInk(color);
        Tip tip = new Tip(5, TipType.FOUNTAIN);
        return new FountainPen.Builder()
                .setCanFillInk(true)
                .setWriteBehaviour(new SmoothWriteBehavior())
                .setInk(ink)
                .setTip(tip)
                .setBrand("reynolds")
                .setName("fludo pro")
                .setPrice(80)
                .build();
    }
}
