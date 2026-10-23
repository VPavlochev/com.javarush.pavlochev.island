package com.javarush.pavlochev.island.model;

import com.javarush.pavlochev.island.model.base.Organism;

public class Plant extends Organism {
    private double weight;

    public Plant() {
        super("Plant");
        this.weight = 1.0;
    }

    public double getWeight() {
        return weight;
    }
}
