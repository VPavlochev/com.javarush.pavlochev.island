package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Goat extends Herbivore {
    public Goat() {
        super("Goat", Configuration.GOAT_WEIGHT, Configuration.GOAT_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_GOAT;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.GOAT_MOVE_DISTANCE;
    }
}