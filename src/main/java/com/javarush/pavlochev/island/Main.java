package com.javarush.pavlochev.island;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import com.javarush.pavlochev.island.model.animals.*;
import com.javarush.pavlochev.island.tasks.DayRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Запуск симуляции острова {}x{}", Configuration.X, Configuration.Y);

        Location[][] locations = new Location[Configuration.X][Configuration.Y];
        for (int i = 0; i < Configuration.X; i++) {
            for (int j = 0; j < Configuration.Y; j++) {
                locations[i][j] = new Location();
            }
        }

        populateIsland(locations);

        ExecutorService workerPool = Executors.newFixedThreadPool(4);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        DayRunner dayRunner = new DayRunner(locations, workerPool);
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(dayRunner, 0, 3, TimeUnit.SECONDS);

        Thread.sleep(30000);
        scheduledFuture.cancel(false);
        workerPool.shutdown();
        scheduler.shutdown();

        log.info("Симуляция завершена");
    }

    private static void populateIsland(Location[][] locations) {
        for (Location[] row : locations) {
            for (Location location : row) {
                for (int i = 0; i < Configuration.MAX_PLANT; i++) {
                    location.addPlant(new Plant());
                }
            }
        }

        addAnimals(locations, Wolf.class, Configuration.MAX_WOLF / 2);
        addAnimals(locations, Bear.class, Configuration.MAX_BEAR / 2);
        addAnimals(locations, Eagle.class, Configuration.MAX_EAGLE / 2);
        addAnimals(locations, Snake.class, Configuration.MAX_SNAKE / 2);
        addAnimals(locations, Fox.class, Configuration.MAX_FOX / 2);

        addAnimals(locations, Rabbit.class, Configuration.MAX_RABBIT / 2);
        addAnimals(locations, Horse.class, Configuration.MAX_HORSE / 2);
        addAnimals(locations, Mouse.class, Configuration.MAX_MOUSE / 2);
        addAnimals(locations, Deer.class, Configuration.MAX_DEER / 2);
        addAnimals(locations, Sheep.class, Configuration.MAX_SHEEP / 2);
        addAnimals(locations, Boar.class, Configuration.MAX_BOAR / 2);
        addAnimals(locations, Buffalo.class, Configuration.MAX_BUFFALO / 2);
        addAnimals(locations, Goat.class, Configuration.MAX_GOAT / 2);
        addAnimals(locations, Duck.class, Configuration.MAX_DUCK / 2);
        addAnimals(locations, Caterpillar.class, Configuration.MAX_CATERPILLAR / 2);
    }

    private static void addAnimals(Location[][] locations, Class<? extends com.javarush.pavlochev.island.model.base.Animal> animalClass, int countPerLocation) {
        java.util.concurrent.ThreadLocalRandom random = java.util.concurrent.ThreadLocalRandom.current();

        for (Location[] row : locations) {
            for (Location location : row) {
                if (random.nextDouble() < 0.3) {
                    for (int i = 0; i < countPerLocation; i++) {
                        try {
                            com.javarush.pavlochev.island.model.base.Animal animal =
                                    com.javarush.pavlochev.island.model.factory.AnimalFactory.create(animalClass);
                            location.addAnimal(animal);
                            for (int x = 0; x < locations.length; x++) {
                                for (int y = 0; y < locations[x].length; y++) {
                                    if (locations[x][y] == location) {
                                        animal.setX(x);
                                        animal.setY(y);
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            log.error("Ошибка создания животного: {}", animalClass.getSimpleName(), e);
                        }
                    }
                }
            }
        }
    }
}