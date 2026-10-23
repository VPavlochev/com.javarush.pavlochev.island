package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class DayRunner implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(DayRunner.class);

    private final Location[][] locations;
    private final ExecutorService workerPool;
    private int dayCounter = 0;

    public DayRunner(Location[][] locations, ExecutorService workerPool) {
        this.locations = locations;
        this.workerPool = workerPool;
    }

    @Override
    public void run() {
        dayCounter++;
        log.info("=== Начало игрового дня {} ===", dayCounter);

        new PlantGrowTask(locations).run();

        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(new ArrayList<>(location.getAnimals()));
            }
        }

        CountDownLatch latch = new CountDownLatch(1);
        workerPool.submit(() -> {
            try {
                new AnimalLifeCycleTask(allAnimals, dayCounter).run();
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        new StatisticsTask(locations).run();

        log.info("=== Конец игрового дня {} ===", dayCounter);
    }
}