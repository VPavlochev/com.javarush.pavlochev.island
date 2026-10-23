package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Mouse extends Herbivore {
    public Mouse() {
        super("Mouse", Configuration.MOUSE_WEIGHT, Configuration.MOUSE_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_MOUSE;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.MOUSE_MOVE_DISTANCE;
    }
}
