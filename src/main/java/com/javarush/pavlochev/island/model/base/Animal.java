package com.javarush.pavlochev.island.model.base;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import com.javarush.pavlochev.island.model.factory.AnimalFactory;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public abstract class Animal extends Organism {
    private static final Logger log = LoggerFactory.getLogger(Animal.class);

    private static final AtomicInteger IDS = new AtomicInteger();
    private final int id = IDS.incrementAndGet();

    private volatile boolean alive = true;
    private int lastReproductionDay = -1;

    @Setter
    private double weight;
    private final double maxWeight;

    @Setter
    private Location location;

    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

    protected Animal(String type, double initialWeight, double maxWeight) {
        super(type);
        this.weight = initialWeight;
        this.maxWeight = maxWeight;
    }

    public final boolean isAlive() {
        return alive;
    }

    public final String getLabel() {
        return getClass().getSimpleName() + "#" + id;
    }

    public int getLastReproductionDay() {
        return lastReproductionDay;
    }

    public void setLastReproductionDay(int day) {
        this.lastReproductionDay = day;
    }

    // ========== Публичные методы (точка входа) ==========

    public boolean eat(Organism food) {
        if (!alive || food == null) return false;

        if (food instanceof Plant plant) {
            synchronized (location) { // Синхронизируем, если нужно изменить вес и удалить растение
                this.weight = Math.min(maxWeight, this.weight + plant.getWeight());
                // Растение должно быть удалено из локации, но пока оставим просто поедание
                return true;
            }
        }
        return false;
    }

    public Animal multiply(Location currentLocation, int currentDay) {
        if (currentLocation == null) return null;

        // Блокируем локацию, чтобы проверка максимума и добавление ребёнка были атомарными
        synchronized (currentLocation) {
            return multiplyUnderLock(currentLocation, currentDay);
        }
    }

    public void die() {
        if (location != null) {
            synchronized (location) {
                dieUnderLock();
            }
        } else {
            this.alive = false;
        }
    }

    public boolean consumeEnergy() {
        if (!alive) return true;

        if (location != null) {
            synchronized (location) {
                return consumeEnergyUnderLock();
            }
        }
        return false;
    }

    public void move() {
        if (!alive || location == null) return;

        int dx = ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);
        int dy = ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);

        int newX = this.x + dx;
        int newY = this.y + dy;

        if (newX < 0 || newX >= Configuration.X || newY < 0 || newY >= Configuration.Y) {
            return;
        }

        Location targetLocation = com.javarush.pavlochev.island.model.Island.getInstance().getLocation(newX, newY);

        // Для перемещения используем синхронизированные методы Location,
        // чтобы избежать deadlock при блокировке двух локаций сразу
        if (!targetLocation.isMaxFor(this)) {
            synchronized (this.location) {
                this.location.removeAnimalUnderLock(this);
            }
            synchronized (targetLocation) {
                targetLocation.addAnimalUnderLock(this);
            }
            this.location = targetLocation;
            this.x = newX;
            this.y = newY;
        }
    }

    // ========== UnderLock методы (вызываются внутри synchronized) ==========

    private Animal multiplyUnderLock(Location currentLocation, int currentDay) {
        if (!alive) return null;
        if (this.lastReproductionDay == currentDay) return null;
        if (currentLocation.isMaxForUnderLock(this)) return null;

        Animal mate = currentLocation.animalsUnderLock().stream()
                .filter(a -> a != this &&
                        a.isAlive() &&
                        a.getClass() == this.getClass() &&
                        a.getLastReproductionDay() != currentDay)
                .findFirst()
                .orElse(null);

        if (mate == null) return null;

        this.lastReproductionDay = currentDay;
        mate.setLastReproductionDay(currentDay);

        Animal child = AnimalFactory.create(this.getClass());
        currentLocation.addAnimalUnderLock(child);
        child.setX(this.x);
        child.setY(this.y);

        log.debug("Размножение: {} и {} создали {}", this.getLabel(), mate.getLabel(), child.getLabel());
        return child;
    }

    private void dieUnderLock() {
        this.alive = false;
        if (location != null) {
            location.removeAnimalUnderLock(this);
            this.location = null;
        }
        log.debug("Смерть: {}", this.getLabel());
    }

    private boolean consumeEnergyUnderLock() {
        if (!alive) return true;

        this.weight -= Configuration.WEIGHT_LOSS_PER_TICK;
        if (this.weight <= 0) {
            dieUnderLock();
            return true;
        }
        return false;
    }

    protected abstract int getMaxPerLocation();
    protected abstract int getMoveDistance();
}