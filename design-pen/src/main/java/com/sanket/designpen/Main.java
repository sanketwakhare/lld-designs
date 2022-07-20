package com.sanket.designpen;

import com.sanket.designpen.factories.PenFactory;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.pens.Pen;

public class Main {

    public static void main(String[] args) throws Exception {

        Pen bluePen = PenFactory.getReynoldsRacerGelPen(InkColor.BLUE);
        bluePen.write();
        System.out.println(bluePen.getColor());

        Pen whiteBoardMarker = PenFactory.getCamlinWhiteBoardMarker(InkColor.GREEN);
        whiteBoardMarker.write();
        System.out.println(whiteBoardMarker.getColor());

        Pen permanentMarker = PenFactory.getCamlinPermanentMarker(InkColor.BLACK);
        permanentMarker.write();
        System.out.println(permanentMarker.getColor());

        Pen fountainPen = PenFactory.getReynoldsFludoProFountainPen(InkColor.BLUE);
        fountainPen.write();
        System.out.println(fountainPen.getColor());

        System.out.println("done");
    }
}
