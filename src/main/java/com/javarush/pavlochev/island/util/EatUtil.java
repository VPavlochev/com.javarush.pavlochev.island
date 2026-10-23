package com.javarush.pavlochev.island.util;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.concurrent.ThreadLocalRandom;

public final class EatUtil {
    private EatUtil() {}

    // Рассчитывает вероятность поедания жертвы хищником.

    public static boolean isEat(Animal predator, Animal prey) {
        int chance = getProbability(predator, prey);
        if (chance == 0) return false;

        // Бросаем кубик от 0 до 99. Если выпало меньше шанса - успех.
        return ThreadLocalRandom.current().nextInt(100) < chance;
    }

    private static int getProbability(Animal predator, Animal prey) {
        String p = predator.getClass().getSimpleName();
        String pr = prey.getClass().getSimpleName();

        // Волк
        if (p.equals("Wolf")) {
            if (pr.equals("Rabbit")) return Configuration.WOLF_EATS_RABBIT;
            if (pr.equals("Horse")) return Configuration.WOLF_EATS_HORSE;
            if (pr.equals("Mouse")) return Configuration.WOLF_EATS_MOUSE;
            if (pr.equals("Deer")) return Configuration.WOLF_EATS_DEER;
            if (pr.equals("Sheep")) return Configuration.WOLF_EATS_SHEEP;
            if (pr.equals("Boar")) return Configuration.WOLF_EATS_BOAR;
            if (pr.equals("Buffalo")) return Configuration.WOLF_EATS_BUFFALO;
            if (pr.equals("Goat")) return Configuration.WOLF_EATS_GOAT;
            if (pr.equals("Duck")) return Configuration.WOLF_EATS_DUCK;
        }
        // Медведь
        if (p.equals("Bear")) {
            if (pr.equals("Rabbit")) return Configuration.BEAR_EATS_RABBIT;
            if (pr.equals("Horse")) return Configuration.BEAR_EATS_HORSE;
            if (pr.equals("Mouse")) return Configuration.BEAR_EATS_MOUSE;
            if (pr.equals("Deer")) return Configuration.BEAR_EATS_DEER;
            if (pr.equals("Sheep")) return Configuration.BEAR_EATS_SHEEP;
            if (pr.equals("Boar")) return Configuration.BEAR_EATS_BOAR;
            if (pr.equals("Buffalo")) return Configuration.BEAR_EATS_BUFFALO;
            if (pr.equals("Goat")) return Configuration.BEAR_EATS_GOAT;
            if (pr.equals("Duck")) return Configuration.BEAR_EATS_DUCK;
        }
        // Орел
        if (p.equals("Eagle")) {
            if (pr.equals("Rabbit")) return Configuration.EAGLE_EATS_RABBIT;
            if (pr.equals("Mouse")) return Configuration.EAGLE_EATS_MOUSE;
            if (pr.equals("Duck")) return Configuration.EAGLE_EATS_DUCK;
            if (pr.equals("Caterpillar")) return Configuration.EAGLE_EATS_CATERPILLAR;
        }
        // Змея
        if (p.equals("Snake")) {
            if (pr.equals("Rabbit")) return Configuration.SNAKE_EATS_RABBIT;
            if (pr.equals("Mouse")) return Configuration.SNAKE_EATS_MOUSE;
            if (pr.equals("Duck")) return Configuration.SNAKE_EATS_DUCK;
            if (pr.equals("Caterpillar")) return Configuration.SNAKE_EATS_CATERPILLAR;
        }
        // Лиса
        if (p.equals("Fox")) {
            if (pr.equals("Rabbit")) return Configuration.FOX_EATS_RABBIT;
            if (pr.equals("Mouse")) return Configuration.FOX_EATS_MOUSE;
            if (pr.equals("Duck")) return Configuration.FOX_EATS_DUCK;
            if (pr.equals("Caterpillar")) return Configuration.FOX_EATS_CATERPILLAR;
        }

        return 0; // Хищник не ест эту жертву
    }
}