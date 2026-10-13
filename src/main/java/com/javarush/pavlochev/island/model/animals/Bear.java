package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Predator;

public class Bear extends Predator {
    public Bear() {
        super("Bear", Configuration.INITIAL_WEIGHT, Configuration.MAX_WEIGHT_BEAR);
    }

    @Override
    public Bear multiply() {
        return new Bear();
    }
}
