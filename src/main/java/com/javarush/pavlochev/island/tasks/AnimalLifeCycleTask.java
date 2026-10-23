package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Задача жизненного цикла животных на один игровой день.
 */
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
            // Если животное уже мертво, пропускаем его
            if (!animal.isAlive()) {
                continue;
            }

            Location currentLocation = animal.getLocation();

            // 1. Размножение (теперь передаем локацию и текущий день)
            if (currentLocation != null) {
                animal.multiply(currentLocation, currentDay);
            }

            // 2. Перемещение (внутри move() уже есть проверки на alive и location)
            animal.move();

            // 3. Потеря веса (голод). Если вес упадет до 0, внутри вызовется die()
            animal.consumeEnergy();
        }
    }
}