package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StatisticsTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(StatisticsTask.class);

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

        log.info("--- Статистика острова ---");
        stats.forEach((animalClass, count) ->
                log.info("{}: {}", animalClass.getSimpleName(), count)
        );
        log.info("--------------------------");
    }
}