package com.sanket.designsnakeandladder.factories;

import com.sanket.designsnakeandladder.strategies.OneDiceRollUnlockButtonStrategy;
import com.sanket.designsnakeandladder.strategies.SixDiceRollUnlockButtonStrategy;
import com.sanket.designsnakeandladder.strategies.UnlockButtonStrategy;
import com.sanket.designsnakeandladder.strategies.UnlockButtonStrategyType;

public class UnlockDiceRollStrategyFactory {

    public static UnlockButtonStrategy getStrategyInstance(UnlockButtonStrategyType type) {
        return switch (type) {
            case ONE_DICE_ROLL -> new OneDiceRollUnlockButtonStrategy();
            case SIX_DICE_ROLL -> new SixDiceRollUnlockButtonStrategy();
        };
    }
}
