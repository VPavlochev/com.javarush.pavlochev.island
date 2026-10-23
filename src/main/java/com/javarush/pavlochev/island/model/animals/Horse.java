package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Horse extends Herbivore {
    public Horse() {
        super("Horse", Configuration.INITIAL_WEIGHT, Configuration.MAX_WEIGHT_HORSE);
    }

    @Override
    public Horse multiply() {
        return new Horse();
    }
}
