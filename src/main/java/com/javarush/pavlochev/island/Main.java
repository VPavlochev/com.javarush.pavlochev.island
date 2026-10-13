package com.javarush.pavlochev.island;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Animal;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import com.javarush.pavlochev.island.model.animals.Bear;
import com.javarush.pavlochev.island.model.animals.Horse;
import com.javarush.pavlochev.island.model.animals.Rabbit;
import com.javarush.pavlochev.island.model.animals.Wolf;
import com.javarush.pavlochev.island.util.EatUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Создаём одну локацию
        Location location = new Location();

        // 2. Заполняем растениями
        for (int i = 0; i < Configuration.MAX_PLANT; i++) {
            location.addPlant(new Plant());
        }

        // 3. Заполняем животными (по паре каждого вида)
        location.addAnimal(new Wolf());
        location.addAnimal(new Wolf());
        location.addAnimal(new Bear());
        location.addAnimal(new Bear());
        location.addAnimal(new Rabbit());
        location.addAnimal(new Rabbit());
        location.addAnimal(new Horse());
        location.addAnimal(new Horse());

        // 4. Игровой цикл — 3 секунды
        long startTime = System.currentTimeMillis();
        long durationMs = 3000;
        int tick = 0;

        while (System.currentTimeMillis() - startTime < durationMs) {
            tick++;
            System.out.println("=== Такт " + tick + " ===");

            // Поедание: хищники пытаются съесть жертв на той же локации
            List<Animal> animals = location.getAnimals();
            for (Animal animal : animals) {
                if (animal instanceof Wolf || animal instanceof Bear) {
                    for (Animal potentialPrey : animals) {
                        if (potentialPrey != animal && EatUtil.isEat(animal, potentialPrey)) {
                            animal.setWeight(animal.getWeight() + potentialPrey.getWeight());
                            potentialPrey.die();
                            System.out.println(animal.getType() + " съел " + potentialPrey.getType());
                            break;
                        }
                    }
                } else if (animal instanceof Rabbit || animal instanceof Horse) {
                    List<Plant> plants = location.getPlants();
                    if (!plants.isEmpty()) {
                        animal.setWeight(animal.getWeight() + plants.get(0).getWeight());
                        System.out.println(animal.getType() + " съел растение");
                    }
                }
            }

            // Размножение: если есть пара одного вида и не достигнут максимум
            for (Animal animal : location.getAnimals()) {
                long count = location.countAnimalsByType(animal.getType());
                if (count >= 2) {
                    int max = getMaxForType(animal.getType());
                    if (count < max && Math.random() < 0.2) {
                        Animal offspring = animal.multiply();
                        if (offspring != null) {
                            location.addAnimal(offspring);
                            System.out.println("Родился " + offspring.getType());
                        }
                    }
                }
            }

            // Такт: потеря веса, смерть от голода
            for (Animal animal : location.getAnimals()) {
                if (animal.tick()) {
                    System.out.println(animal.getType() + " умер от голода");
                }
            }

            // Статистика
            printStats(location);

            Thread.sleep(500);
        }

        System.out.println("Игра остановлена через 3 секунды. Всего тактов: " + tick);
    }

    private static int getMaxForType(String type) {
        if (type.equals("Wolf")) return Configuration.MAX_WOLF;
        if (type.equals("Bear")) return Configuration.MAX_BEAR;
        if (type.equals("Rabbit")) return Configuration.MAX_RABBIT;
        if (type.equals("Horse")) return Configuration.MAX_HORSE;
        return Integer.MAX_VALUE;
    }

    private static void printStats(Location location) {
        System.out.println("--- Статистика ---");
        System.out.println("Растений: " + location.getPlants().size());
        System.out.println("Wolf: " + location.countAnimalsByType("Wolf"));
        System.out.println("Bear: " + location.countAnimalsByType("Bear"));
        System.out.println("Rabbit: " + location.countAnimalsByType("Rabbit"));
        System.out.println("Horse: " + location.countAnimalsByType("Horse"));
    }
}