package com.javarush.pavlochev.island.model;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Animal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


 // Клетка игрового поля.
 // Потокобезопасна: использует CopyOnWriteArrayList для животных и растений.
public class Location {
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setLocation(null);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    // Проверяет, достигнут ли максимум особей данного вида на локации.

    public boolean isMaxFor(Animal animal) {
        long count = animals.stream()
                .filter(a -> a.getClass().equals(animal.getClass()))
                .count();
        return count >= getMaxPerLocation(animal);
    }

    private int getMaxPerLocation(Animal animal) {
        String className = animal.getClass().getSimpleName();
        return switch (className) {
            case "Wolf" -> Configuration.MAX_WOLF;
            case "Bear" -> Configuration.MAX_BEAR;
            case "Eagle" -> Configuration.MAX_EAGLE;
            case "Snake" -> Configuration.MAX_SNAKE;
            case "Fox" -> Configuration.MAX_FOX;
            case "Rabbit" -> Configuration.MAX_RABBIT;
            case "Horse" -> Configuration.MAX_HORSE;
            case "Mouse" -> Configuration.MAX_MOUSE;
            case "Deer" -> Configuration.MAX_DEER;
            case "Sheep" -> Configuration.MAX_SHEEP;
            case "Boar" -> Configuration.MAX_BOAR;
            case "Buffalo" -> Configuration.MAX_BUFFALO;
            case "Goat" -> Configuration.MAX_GOAT;
            case "Duck" -> Configuration.MAX_DUCK;
            case "Caterpillar" -> Configuration.MAX_CATERPILLAR;
            default -> 0;
        };
    }

    // Подсчёт животных конкретного вида на локации.

    public long countType(Class<? extends Animal> animalClass) {
        return animals.stream()
                .filter(a -> a.getClass().equals(animalClass))
                .count();
    }
}
