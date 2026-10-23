package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Wolf extends Predator {
    public Wolf() {
        super("Wolf", Configuration.WOLF_WEIGHT, Configuration.WOLF_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_WOLF;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.WOLF_MOVE_DISTANCE;
    }
}