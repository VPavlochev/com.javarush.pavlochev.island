package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.HashMap;
import java.util.Map;

// Задача вывода статистики по животным на острове.
// Считает количество особей каждого вида по всей матрице локаций.

public class StatisticsTask implements Runnable {
    private final Location[][] locations;

    public StatisticsTask(Location[][] locations) {
        this.locations = locations;
    }

    @Override
    public void run() {
        Map<Class<? extends Animal>, Integer> stats = new HashMap<>();

        for (Location[] row : locations) {
            for (Location location : row) {
                for (Animal animal : location.getAnimals()) {
                    stats.put(animal.getClass(), stats.getOrDefault(animal.getClass(), 0) + 1);
                }
            }
        }

        System.out.println("--- Статистика острова ---");
        stats.forEach((animalClass, count) ->
                System.out.println(animalClass.getSimpleName() + ": " + count)
        );
        System.out.println("--------------------------");
    }
}
