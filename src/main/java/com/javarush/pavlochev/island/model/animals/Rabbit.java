package com.javarush.pavlochev.island.model.animals;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Rabbit", Configuration.INITIAL_WEIGHT, Configuration.MAX_WEIGHT_RABBIT);
    }

}
