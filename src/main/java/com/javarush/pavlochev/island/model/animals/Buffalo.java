package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super("Buffalo", Configuration.BUFFALO_WEIGHT, Configuration.BUFFALO_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_BUFFALO;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.BUFFALO_MOVE_DISTANCE;
    }
}