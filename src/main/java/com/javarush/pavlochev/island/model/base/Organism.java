package com.javarush.pavlochev.island.model.base;

public abstract class Organism {
    private final String type;

    protected Organism(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
