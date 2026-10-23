package com.javarush.pavlochev.island.model.base;

import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.util.EatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Predator extends Animal {
    private static final Logger log = LoggerFactory.getLogger(Predator.class);

    protected Predator(String type, double initialWeight, double maxWeight) {
        super(type, initialWeight, maxWeight);
    }

    // Хищник ищет жертву на текущей локации и пытается её съесть.

    public void hunt(Location currentLocation) {
        if (currentLocation == null || !isAlive()) return;

        // Ищем любое живое травоядное на локации
        Animal prey = currentLocation.getAnimals().stream()
                .filter(a -> a != this && a.isAlive() && a instanceof Herbivore)
                .findFirst()
                .orElse(null);

        if (prey != null) {
            // Проверяем вероятность поедания
            if (EatUtil.isEat(this, prey)) {
                // Синхронизируем, чтобы жертва не умерла дважды и вес обновился корректно
                synchronized (currentLocation) {
                    if (prey.isAlive()) {
                        // Хищник набирает вес (50% от веса жертвы, но не больше максимума)
                        this.setWeight(Math.min(getMaxWeight(), this.getWeight() + prey.getWeight() * 0.5));
                        prey.die(); // Жертва умирает
                        log.debug("{} съел {}", this.getLabel(), prey.getLabel());
                    }
                }
            }
        }
    }
}