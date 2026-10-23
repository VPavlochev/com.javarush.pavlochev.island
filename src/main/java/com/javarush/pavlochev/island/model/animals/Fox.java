package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Fox extends Predator {
    public Fox() {
        super("Fox", Configuration.FOX_WEIGHT, Configuration.FOX_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_FOX;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.FOX_MOVE_DISTANCE;
    }
}