package com.javarush.pavlochev.island.model;

import com.javarush.pavlochev.island.model.base.Animal;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }

    public List<Plant> getPlants() {
        return List.copyOf(plants);
    }

    public long countAnimalsByType(String type) {
        return animals.stream()
                .filter(a -> a.getType().equals(type))
                .count();
    }
}
