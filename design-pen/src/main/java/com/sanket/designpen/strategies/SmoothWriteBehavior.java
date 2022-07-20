package com.sanket.designpen.strategies;

public class SmoothWriteBehavior implements WriteBehaviour {
    @Override
    public void write() {
        System.out.println("writing smoothly");
    }
}
