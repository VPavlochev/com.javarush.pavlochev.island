package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.base.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

// Отвечает за симуляцию одного игрового дня.
// Запускается по расписанию через ScheduledExecutorService.

public class DayRunner implements Runnable {
    private final Location[][] locations;
    private final ExecutorService workerPool;

    public DayRunner(Location[][] locations, ExecutorService workerPool) {
        this.locations = locations;
        this.workerPool = workerPool;
    }

    @Override
    public void run() {
        System.out.println("=== Начало игрового дня ===");

        // 1. Рост растений (выполняем в основном потоке, так как операция быстрая)
        new PlantGrowTask(locations).run();

        // 2. Сбор всех животных (снимок на начало дня)
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                // Создаем копию списка, чтобы избежать ConcurrentModificationException
                allAnimals.addAll(new ArrayList<>(location.getAnimals()));
            }
        }

        // 3. Жизненный цикл животных в пуле потоков
        // Используем CountDownLatch, чтобы дождаться завершения задач перед статистикой
        CountDownLatch latch = new CountDownLatch(1);
        workerPool.submit(() -> {
            try {
                new AnimalLifeCycleTask(allAnimals).run();
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await(); // Ждем завершения обработки животных
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 4. Статистика (выводим только после завершения всех задач дня)
        new StatisticsTask(locations).run();

        System.out.println("=== Конец игрового дня ===\n");
    }
}
