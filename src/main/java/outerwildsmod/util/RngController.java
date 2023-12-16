package outerwildsmod.util;

import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;

import java.util.Random;

public class RngController {

    public RngController() { }

    public static boolean getCoinFlip(int percentageTrue) {
        return new Random().nextInt(100) < percentageTrue;
    }

    public static boolean getCoingFlip() {
        return RngController.getCoinFlip(50);
    }

    public static int RandInRange(int range) { return new Random().nextInt(range); }
}
