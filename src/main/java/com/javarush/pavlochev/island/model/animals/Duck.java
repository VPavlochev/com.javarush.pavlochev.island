package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Duck extends Herbivore {
    public Duck() {
        super("Duck", Configuration.DUCK_WEIGHT, Configuration.DUCK_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_DUCK;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.DUCK_MOVE_DISTANCE;
    }
}
