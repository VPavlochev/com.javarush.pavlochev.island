package com.javarush.pavlochev.island.util;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Animal;

public final class AnimalEmoji {
    private AnimalEmoji() {}

    // Возвращает эмодзи для животного.

    public static String getEmoji(Animal animal) {
        String className = animal.getClass().getSimpleName();
        return switch (className) {
            case "Wolf" -> Configuration.EMOJI_WOLF;
            case "Bear" -> Configuration.EMOJI_BEAR;
            case "Eagle" -> Configuration.EMOJI_EAGLE;
            case "Snake" -> Configuration.EMOJI_SNAKE;
            case "Fox" -> Configuration.EMOJI_FOX;
            case "Rabbit" -> Configuration.EMOJI_RABBIT;
            case "Horse" -> Configuration.EMOJI_HORSE;
            case "Mouse" -> Configuration.EMOJI_MOUSE;
            case "Deer" -> Configuration.EMOJI_DEER;
            case "Sheep" -> Configuration.EMOJI_SHEEP;
            case "Boar" -> Configuration.EMOJI_BOAR;
            case "Buffalo" -> Configuration.EMOJI_BUFFALO;
            case "Goat" -> Configuration.EMOJI_GOAT;
            case "Duck" -> Configuration.EMOJI_DUCK;
            case "Caterpillar" -> Configuration.EMOJI_CATERPILLAR;
            default -> "❓";
        };
    }
}
