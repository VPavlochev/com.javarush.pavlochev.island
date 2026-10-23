package com.javarush.pavlochev.island.model;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.base.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Клетка игрового поля.
// Все публичные методы синхронизированы.
// Методы с суффиксом UnderLock вызываются только внутри synchronized-блока.

public class Location {
    private static final Logger log = LoggerFactory.getLogger(Location.class);

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    // ========== Публичные методы (захватывают lock) ==========

    public synchronized void addAnimal(Animal animal) {
        addAnimalUnderLock(animal);
    }

    public synchronized void removeAnimal(Animal animal) {
        removeAnimalUnderLock(animal);
    }

    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
    }

    public synchronized void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public synchronized List<Animal> getAnimals() {
        return new ArrayList<>(animals); // Возвращаем копию для безопасности
    }

    public synchronized List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public synchronized boolean isMaxFor(Animal animal) {
        return isMaxForUnderLock(animal);
    }

    public synchronized long countType(Class<? extends Animal> animalClass) {
        return countTypeUnderLock(animalClass);
    }

    // ========== UnderLock методы (вызываются внутри synchronized) ==========

    // Возвращает список животных. Вызывать только внутри synchronized(location).

    public List<Animal> animalsUnderLock() {
        return animals;
    }

    // Возвращает список растений. Вызывать только внутри synchronized(location).

    public List<Plant> plantsUnderLock() {
        return plants;
    }

    // Считает количество животных конкретного вида.
    // Вызывать только внутри synchronized(location).

    public long countTypeUnderLock(Class<? extends Animal> animalClass) {
        return animals.stream()
                .filter(a -> a.getClass().equals(animalClass))
                .count();
    }

    // Проверяет, достигнут ли максимум особей данного вида.
    // Вызывать только внутри synchronized(location).

    public boolean isMaxForUnderLock(Animal animal) {
        long count = countTypeUnderLock(animal.getClass());
        return count >= getMaxPerLocation(animal);
    }

    // Добавляет животное. Вызывать только внутри synchronized(location).

    public void addAnimalUnderLock(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    // Удаляет животное. Вызывать только внутри synchronized(location).

    public void removeAnimalUnderLock(Animal animal) {
        animals.remove(animal);
        animal.setLocation(null);
    }

    // ========== Приватные методы ==========

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
}
