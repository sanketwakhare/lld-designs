package com.sanket.designsnakeandladder.strategies;

public class SixDiceRollUnlockButtonStrategy implements UnlockButtonStrategy {

    @Override
    public boolean canUnlock(int value) {
        return value == 6;
    }
}
