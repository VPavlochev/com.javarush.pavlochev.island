package com.javarush.pavlochev.island.model.base;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Animal extends Organism {

    @Setter
    private double weight;
    private final double maxWeight;
    @Setter
    private Location location;
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

    protected abstract int getMaxPerLocation();
    protected abstract int getMoveDistance();

    protected Animal(String type, double initialWeight, double maxWeight) {
        super(type);
        this.weight = initialWeight;
        this.maxWeight = maxWeight;
    }

    public boolean eat(Organism food) {
        if (food instanceof Plant plant) {
            setWeight(this.weight + plant.getWeight());
            return true;
        }
        return false;
    }

    public Animal multiply() {
        return com.javarush.pavlochev.island.model.factory.AnimalFactory.create(this.getClass());

    }

    public void die() {
        if (location != null) {
            location.removeAnimal(this);
        }
    }

    public boolean consumeEnergy() {
        this.weight -= Configuration.WEIGHT_LOSS_PER_TICK;
        if (this.weight <= 0) {
            die();
            return true;
        }
        return false;
    }

    public void move() {
        // 1. Случайное направление и дистанция
        int dx = java.util.concurrent.ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);
        int dy = java.util.concurrent.ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);

        int newX = this.x + dx;
        int newY = this.y + dy;

        // 2. Проверка границ острова
        if (newX < 0 || newX >= com.javarush.pavlochev.island.config.Configuration.X ||
                newY < 0 || newY >= com.javarush.pavlochev.island.config.Configuration.Y) {
            return; // Остаёмся на месте
        }

        // 3. Получаем целевую локацию
        com.javarush.pavlochev.island.model.Island targetIsland = com.javarush.pavlochev.island.model.Island.getInstance();
        com.javarush.pavlochev.island.model.Location targetLocation = targetIsland.getLocation(newX, newY);

        // 4. Проверяем, есть ли место в целевой локации
        if (targetLocation.isMaxFor(this)) {
            return; // Остаёмся на месте
        }

        // 5. Перемещаемся
        this.location.removeAnimal(this);
        targetLocation.addAnimal(this);
        this.location = targetLocation;
        this.x = newX;
        this.y = newY;
    }
}
