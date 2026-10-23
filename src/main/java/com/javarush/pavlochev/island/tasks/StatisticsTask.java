package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.util.AnimalEmoji;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;
import com.javarush.pavlochev.island.model.base.Predator;
import com.javarush.pavlochev.island.model.base.Herbivore;
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
        int totalPredators = 0;
        int totalHerbivores = 0;
        int totalPlants = 0;

        for (Location[] row : locations) {
            for (Location location : row) {
                for (Animal animal : location.getAnimals()) {
                    stats.put(animal.getClass(), stats.getOrDefault(animal.getClass(), 0) + 1);

                    if (animal instanceof Predator) totalPredators++;
                    if (animal instanceof Herbivore) totalHerbivores++;
                }
                totalPlants += location.getPlants().size();
            }
        }

        log.info("=== Статистика острова ===");
        log.info("Всего хищников: {} {}", totalPredators, Configuration.EMOJI_WOLF);
        log.info("Всего травоядных: {} {}", totalHerbivores, Configuration.EMOJI_RABBIT);
        log.info("Всего растений: {} {}", totalPlants, Configuration.EMOJI_PLANT);
        log.info("--- Повидовая статистика ---");

        stats.forEach((animalClass, count) -> {
            try {
                Animal example = com.javarush.pavlochev.island.model.factory.AnimalFactory.create(animalClass);
                String emoji = AnimalEmoji.getEmoji(example);
                log.info("{} {}: {}", emoji, animalClass.getSimpleName(), count);
            } catch (Exception e) {
                log.info("❓ {}: {}", animalClass.getSimpleName(), count);
            }
        });


        log.info("==========================");
    }
}
