package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Bear extends Predator {
    public Bear() {
        super("Bear", Configuration.BEAR_WEIGHT, Configuration.BEAR_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_BEAR;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.BEAR_MOVE_DISTANCE;
    }
}
