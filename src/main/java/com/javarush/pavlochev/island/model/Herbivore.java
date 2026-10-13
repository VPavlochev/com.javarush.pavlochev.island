package com.javarush.pavlochev.island.model;

public abstract class Herbivore extends Animal {
    protected Herbivore(String type, double initialWeight, double maxWeight) {
        super(type, initialWeight, maxWeight);
    }
}