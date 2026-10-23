package com.javarush.pavlochev.island;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import com.javarush.pavlochev.island.model.animals.*;
import com.javarush.pavlochev.island.tasks.DayRunner;

import java.util.concurrent.*;

// Точка входа в симуляцию острова.
// Запускает многопоточную симуляцию через ScheduledExecutorService.

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Создаём остров (матрицу локаций)
        Location[][] locations = new Location[Configuration.X][Configuration.Y];
        for (int i = 0; i < Configuration.X; i++) {
            for (int j = 0; j < Configuration.Y; j++) {
                locations[i][j] = new Location();
            }
        }

        // 2. Заполняем остров растениями и животными
        populateIsland(locations);

        // 3. Создаём пул рабочих потоков для AnimalLifeCycleTask
        ExecutorService workerPool = Executors.newFixedThreadPool(4);

        // 4. Создаём ScheduledExecutorService для запуска игровых дней
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 5. Запускаем DayRunner каждые 3 секунды
        DayRunner dayRunner = new DayRunner(locations, workerPool);
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(dayRunner, 0, 3, TimeUnit.SECONDS);

        // 6. Останавливаем симуляцию через 30 секунд (10 игровых дней)
        Thread.sleep(30000);
        scheduledFuture.cancel(false);
        workerPool.shutdown();
        scheduler.shutdown();

        System.out.println("Симуляция завершена.");
    }

    // Заполняет остров растениями и животными.
    // Растения: MAX_PLANT на каждой локации.
    // Животные: примерно 50% от MAX для каждого вида на случайных локациях.

    private static void populateIsland(Location[][] locations) {
        // Заполняем растениями
        for (Location[] row : locations) {
            for (Location location : row) {
                for (int i = 0; i < Configuration.MAX_PLANT; i++) {
                    location.addPlant(new Plant());
                }
            }
        }

        // Заполняем животными (50% от максимума на случайных локациях)
        // Хищники
        addAnimals(locations, Wolf.class, Configuration.MAX_WOLF / 2);
        addAnimals(locations, Bear.class, Configuration.MAX_BEAR / 2);
        addAnimals(locations, Eagle.class, Configuration.MAX_EAGLE / 2);
        addAnimals(locations, Snake.class, Configuration.MAX_SNAKE / 2);
        addAnimals(locations, Fox.class, Configuration.MAX_FOX / 2);

        // Травоядные
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

    // Добавляет животных указанного класса на случайные локации.

    private static void addAnimals(Location[][] locations, Class<? extends com.javarush.pavlochev.island.model.base.Animal> animalClass, int countPerLocation) {
        java.util.concurrent.ThreadLocalRandom random = java.util.concurrent.ThreadLocalRandom.current();

        for (Location[] row : locations) {
            for (Location location : row) {
                // Случайно решаем, заполнять ли эту локацию (30% шанс)
                if (random.nextDouble() < 0.3) {
                    for (int i = 0; i < countPerLocation; i++) {
                        try {
                            com.javarush.pavlochev.island.model.base.Animal animal =
                                    com.javarush.pavlochev.island.model.factory.AnimalFactory.create(animalClass);
                            location.addAnimal(animal);
                            // Устанавливаем координаты
                            // Находим координаты локации в матрице
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
                            System.err.println("Ошибка создания животного: " + animalClass.getSimpleName());
                        }
                    }
                }
            }
        }
    }
}