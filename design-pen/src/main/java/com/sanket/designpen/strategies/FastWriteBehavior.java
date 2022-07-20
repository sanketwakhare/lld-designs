package com.sanket.designpen.strategies;

public class FastWriteBehavior implements WriteBehaviour {
    @Override
    public void write() {
        System.out.println("writing super fast");
    }
}
