package com.sanket.designpen;

import com.sanket.designpen.factories.PenFactory;
import com.sanket.designpen.models.ink.InkColor;
import com.sanket.designpen.models.pens.Pen;

public class Main {

    public static void main(String[] args) throws Exception {

        Pen bluePen = PenFactory.getReynoldsRacerGelPen(InkColor.BLUE);
        Pen whiteBoardMarker = PenFactory.getCamlinWhiteBoardMarker(InkColor.GREEN);
        Pen permanentMarker = PenFactory.getCamlinPermanentMarker(InkColor.BLACK);

        System.out.println("done");
    }
}
