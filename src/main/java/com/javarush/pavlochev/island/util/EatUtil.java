package com.javarush.pavlochev.island.util;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.animals.Bear;
import com.javarush.pavlochev.island.model.animals.Wolf;
import com.javarush.pavlochev.island.model.animals.Rabbit;
import com.javarush.pavlochev.island.model.animals.Horse;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.concurrent.ThreadLocalRandom;

public final class EatUtil {
    private EatUtil() {}

    public static boolean isEat(Animal predator, Animal prey) {
        int probability = getProbability(predator, prey);
        if (probability == 0) return false;

        int roll = ThreadLocalRandom.current().nextInt(100);
        return roll < probability;
    }

    private static int getProbability(Animal predator, Animal prey) {
        if (predator instanceof Wolf) {
            if (prey instanceof Rabbit) return Configuration.WOLF_EATS_RABBIT;
            if (prey instanceof Horse) return Configuration.WOLF_EATS_HORSE;
        }
        if (predator instanceof Bear) {
            if (prey instanceof Rabbit) return Configuration.BEAR_EATS_RABBIT;
            if (prey instanceof Horse) return Configuration.BEAR_EATS_HORSE;
        }
        return 0;
    }
}
