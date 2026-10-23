package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super("Caterpillar", Configuration.CATERPILLAR_WEIGHT, Configuration.CATERPILLAR_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_CATERPILLAR;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.CATERPILLAR_MOVE_DISTANCE;
    }
}
