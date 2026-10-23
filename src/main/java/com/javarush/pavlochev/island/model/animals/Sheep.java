package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Sheep extends Herbivore {
    public Sheep() {
        super("Sheep", Configuration.SHEEP_WEIGHT, Configuration.SHEEP_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_SHEEP;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.SHEEP_MOVE_DISTANCE;
    }
}
