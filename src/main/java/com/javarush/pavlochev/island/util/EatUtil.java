package com.javarush.pavlochev.island.util;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.concurrent.ThreadLocalRandom;

public final class EatUtil {
    private EatUtil() {}

    public static boolean isEat(Animal predator, Animal prey) {
        int probability = getProbability(predator.getType(), prey.getType());
        if (probability == 0) return false;

        int roll = ThreadLocalRandom.current().nextInt(100);
        return roll < probability;
    }

    private static int getProbability(String predator, String prey) {
        if (predator.equals("Wolf")) {
            if (prey.equals("Rabbit")) return Configuration.WOLF_EATS_RABBIT;
            if (prey.equals("Horse")) return Configuration.WOLF_EATS_HORSE;
        }
        if (predator.equals("Bear")) {
            if (prey.equals("Rabbit")) return Configuration.BEAR_EATS_RABBIT;
            if (prey.equals("Horse")) return Configuration.BEAR_EATS_HORSE;
        }
        return 0;
    }
}
