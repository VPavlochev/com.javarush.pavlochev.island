package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Wolf extends Predator {
    public Wolf() {
        super("Wolf", Configuration.INITIAL_WEIGHT, Configuration.MAX_WEIGHT_WOLF);
    }

    @Override
    public Wolf multiply() {
        return new Wolf();
    }
}