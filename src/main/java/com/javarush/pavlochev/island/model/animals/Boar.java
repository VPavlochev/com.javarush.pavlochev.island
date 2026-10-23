package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Boar extends Herbivore {
    public Boar() {
        super("Boar", Configuration.BOAR_WEIGHT, Configuration.BOAR_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_BOAR;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.BOAR_MOVE_DISTANCE;
    }
}
