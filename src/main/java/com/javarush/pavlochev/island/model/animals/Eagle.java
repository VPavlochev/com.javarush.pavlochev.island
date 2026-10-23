package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Eagle extends Predator {
    public Eagle() {
        super("Eagle", Configuration.EAGLE_WEIGHT, Configuration.EAGLE_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_EAGLE;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.EAGLE_MOVE_DISTANCE;
    }
}
