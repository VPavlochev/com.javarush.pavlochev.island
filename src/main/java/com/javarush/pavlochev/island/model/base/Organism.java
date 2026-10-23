package com.javarush.pavlochev.island.model.base;

public abstract class Organism {
    private final String type;

    // Защищённый конструктор — запрещает прямое создание экземпляров Organism.
    // Создавать объекты могут только наследники: Animal и Plant.
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
