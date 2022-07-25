package com.sanket.designsnakeandladder;

import com.sanket.designsnakeandladder.models.Dice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DesignSnakeAndLadderApplicationTests {

    @Test
    void testRollDice() {
        int n = 100;
        while (n >= 0) {
            Dice dice = new Dice(1, 6);
            int number = dice.rollDice();
            Assert.isTrue(number >= 1 && number <= 6, "Invalid number from a dice roll");
            n--;
        }
    }

}
