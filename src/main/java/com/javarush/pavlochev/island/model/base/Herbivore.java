package com.javarush.pavlochev.island.model.base;

public abstract class Herbivore extends Animal {
    protected Herbivore(String type, double initialWeight, double maxWeight) {
        super(type, initialWeight, maxWeight);
    }

    protected abstract int getMaxPerLocation();
    protected abstract int getMoveDistance();
}