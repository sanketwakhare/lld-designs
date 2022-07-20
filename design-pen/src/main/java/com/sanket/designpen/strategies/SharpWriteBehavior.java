package com.sanket.designpen.strategies;

public class SharpWriteBehavior implements WriteBehaviour {
    @Override
    public void write() {
        System.out.println("writing sharp");
    }
}
