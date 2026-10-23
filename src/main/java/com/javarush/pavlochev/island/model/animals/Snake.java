package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Predator;

public class Snake extends Predator {
    public Snake() {
        super("Snake", Configuration.SNAKE_WEIGHT, Configuration.SNAKE_MAX_WEIGHT);
    }

    @Override
    protected int getMaxPerLocation() {
        return Configuration.MAX_SNAKE;
    }

    @Override
    protected int getMoveDistance() {
        return Configuration.SNAKE_MOVE_DISTANCE;
    }
}
