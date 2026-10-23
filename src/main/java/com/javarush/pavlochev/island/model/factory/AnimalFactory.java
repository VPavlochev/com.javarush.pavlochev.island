package com.javarush.pavlochev.island.model.factory;

import com.javarush.pavlochev.island.model.base.Animal;

import java.lang.reflect.InvocationTargetException;

public final class AnimalFactory {
    private AnimalFactory() {}

    public static Animal create(Class<? extends Animal> animalClass) {
        try {
            return animalClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create animal: " + animalClass.getSimpleName(), e);
        }
    }
}