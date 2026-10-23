package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;
import com.javarush.pavlochev.island.model.base.Predator;
import java.util.ArrayList;
import java.util.List;

// Задача жизненного цикла животных на один игровой день.

public class AnimalLifeCycleTask implements Runnable {
    private final List<Animal> animalsSnapshot;
    private final int currentDay;

    public AnimalLifeCycleTask(List<Animal> animals, int currentDay) {
        this.currentDay = currentDay;
        // Создаем копию списка на момент начала дня, чтобы избежать ConcurrentModificationException
        this.animalsSnapshot = new ArrayList<>(animals);
    }

    @Override
    public void run() {
        for (Animal animal : animalsSnapshot) {
            if (!animal.isAlive()) {
                continue;
            }

            Location currentLocation = animal.getLocation();

            // 1. ОХОТА (только для хищников)
            if (animal instanceof Predator predator) {
                predator.hunt(currentLocation);
            }

            // 2. Размножение
            if (currentLocation != null) {
                animal.multiply(currentLocation, currentDay);
            }

            // 3. Перемещение
            animal.move();

            // 4. Потеря веса (голод)
            animal.consumeEnergy();
        }
    }
}