package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Horse extends Herbivore {
    public Horse() {
        super("Horse", Configuration.HORSE_WEIGHT, Configuration.HORSE_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_HORSE;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.HORSE_MOVE_DISTANCE;
    }
}
