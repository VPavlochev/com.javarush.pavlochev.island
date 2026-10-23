package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Deer extends Herbivore {
    public Deer() {
        super("Deer", Configuration.DEER_WEIGHT, Configuration.DEER_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_DEER;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.DEER_MOVE_DISTANCE;
    }
}