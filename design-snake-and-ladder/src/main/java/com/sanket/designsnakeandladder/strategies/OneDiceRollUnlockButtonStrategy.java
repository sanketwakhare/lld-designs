package com.sanket.designsnakeandladder.strategies;

public class OneDiceRollUnlockButtonStrategy implements UnlockButtonStrategy {

    @Override
    public boolean canUnlock(int value) {
        return value == 1;
    }
}
