package com.javarush.pavlochev.island.model;

public abstract class Predator extends Animal {
    protected Predator(String type, double initialWeight, double maxWeight) {
        super(type, initialWeight, maxWeight);
    }
}