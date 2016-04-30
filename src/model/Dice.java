package model;

import java.util.Random;

/**
 * Created by Ethan on 16/4/29.
 */
public class Dice {
    int value;
    boolean isControlled;
    private static Dice dice;

    private Dice() {
    }

    public static synchronized Dice getInstance() {
        if (dice == null) {
            dice = new Dice();
        }
        return dice;
    }

    public int rollDice() {
        if (isControlled) {
            isControlled = false;
            return value;
        } else {
            Random random = new Random();
            return (random.nextInt(6) + 1);
        }
    }

    public void setValue(int value) {
        this.value = value;
        isControlled = true;
    }
}
