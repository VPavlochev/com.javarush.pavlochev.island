package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Rabbit", Configuration.RABBIT_WEIGHT, Configuration.RABBIT_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_RABBIT;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.RABBIT_MOVE_DISTANCE;
    }
}
