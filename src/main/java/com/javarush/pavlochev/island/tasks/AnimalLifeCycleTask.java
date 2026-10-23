package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.List;

// Задача жизненного цикла животных на один игровой день.
// Обрабатывает список животных: размножение, перемещение, потеря веса.

public class AnimalLifeCycleTask implements Runnable {
    private final List<Animal> animals;

    public AnimalLifeCycleTask(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public void run() {
        for (Animal animal : animals) {
            // Если животное уже мертво (вес <= 0), пропускаем
            if (animal.getWeight() <= 0) {
                continue;
            }

            // 1. Размножение
            Animal child = animal.multiply();
            if (child != null) {
                Location loc = animal.getLocation();
                // Проверяем, есть ли место и жива ли локация
                if (loc != null && !loc.isMaxFor(child)) {
                    loc.addAnimal(child);
                    child.setX(animal.getX());
                    child.setY(animal.getY());
                }
            }

            // 2. Перемещение
            animal.move();

            // 3. Потеря веса (голод)
            animal.consumeEnergy();
        }
    }
}
