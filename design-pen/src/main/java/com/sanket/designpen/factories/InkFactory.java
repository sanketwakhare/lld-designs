package com.sanket.designpen.factories;

import com.sanket.designpen.models.ink.Ink;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.ink.InkType;

public class InkFactory {

    public static Ink getGelInk(InkColor color) throws Exception {
        return new Ink.Builder()
                .setInkType(InkType.GEL)
                .setErasable(false)
                .setColor(color)
                .setDensity(1.2)
                .build();
    }

    public static Ink getWhiteBoardMarkerInk(InkColor color) throws Exception {
        return new Ink.Builder()
                .setInkType(InkType.FLUID)
                .setErasable(true)
                .setColor(color)
                .setDensity(1.2)
                .build();
    }

    public static Ink getPermanentMarkerInk(InkColor color) throws Exception {
        return new Ink.Builder()
                .setInkType(InkType.FLUID)
                .setErasable(false)
                .setColor(color)
                .setDensity(1.2)
                .build();
    }

    public static Ink getFountainInk(InkColor color) throws Exception {
        return new Ink.Builder()
                .setInkType(InkType.FOUNTAIN)
                .setErasable(false)
                .setColor(color)
                .setDensity(1.2)
                .build();
    }

}
