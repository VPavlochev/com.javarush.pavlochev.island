package com.javarush.pavlochev.island.model.base;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;

public abstract class Animal extends Organism {
    private double weight;
    private final double maxWeight;
    private Location location;

    protected Animal(String type, double initialWeight, double maxWeight) {
        super(type);
        this.weight = initialWeight;
        this.maxWeight = maxWeight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = Math.min(weight, maxWeight);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public boolean eat(Organism food) {
        if (food instanceof Plant plant) {
            setWeight(this.weight + plant.getWeight());
            return true;
        }
        return false;
    }

    public Animal multiply() {
        return null;
    }

    public void die() {
        if (location != null) {
            location.removeAnimal(this);
        }
    }

    public boolean consumeEnergy() {
        this.weight -= Configuration.WEIGHT_LOSS_PER_TICK;
        if (this.weight <= 0) {
            die();
            return true;
        }
        return false;
    }
}
