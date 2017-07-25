package rs.ac.uns.ftn.nansi.util;

import java.util.Random;

public class MathUtil {

    private static Random rand = new Random();

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static double randRange(double min, double max) {

        return min + (max - min) * rand.nextDouble();

    }

}
