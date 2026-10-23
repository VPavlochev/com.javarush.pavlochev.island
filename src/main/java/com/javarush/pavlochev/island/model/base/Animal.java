package com.javarush.pavlochev.island.model.base;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;
import com.javarush.pavlochev.island.model.factory.AnimalFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public abstract class Animal extends Organism {

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

    public boolean eat(Organism food) {
        if (!alive || food == null) return false;

        if (food instanceof Plant plant) {
            // Вес не может превысить максимальный
            this.weight = Math.min(maxWeight, this.weight + plant.getWeight());
            return true;
        }
        return false;
    }

    public Animal multiply(Location currentLocation, int currentDay) {
        // 1. Базовые проверки
        if (!alive || currentLocation == null) return null;
        if (this.lastReproductionDay == currentDay) return null; // Уже размножался сегодня
        if (currentLocation.isMaxFor(this)) return null; // Нет места на локации

        // 2. Ищем партнера того же вида, живого и не размножавшегося сегодня
        Animal mate = currentLocation.getAnimals().stream()
                .filter(a -> a != this &&
                        a.isAlive() &&
                        a.getClass() == this.getClass() &&
                        a.getLastReproductionDay() != currentDay)
                .findFirst()
                .orElse(null);

        if (mate == null) return null; // Партнер не найден

        // 3. Оба партнера теперь считаются размножившимися сегодня
        this.lastReproductionDay = currentDay;
        mate.setLastReproductionDay(currentDay);

        // 4. Создаем потомка
        Animal child = AnimalFactory.create(this.getClass());

        // 5. Сразу добавляем потомка на эту же локацию и задаем координаты
        currentLocation.addAnimal(child);
        child.setX(this.x);
        child.setY(this.y);

        return child;
    }

    public void die() {
        this.alive = false;
        if (location != null) {
            location.removeAnimal(this);
            this.location = null; // Обнуляем ссылку, чтобы избежать утечек и ошибок
        }
    }

    public boolean consumeEnergy() {
        if (!alive) return true;

        this.weight -= Configuration.WEIGHT_LOSS_PER_TICK;
        if (this.weight <= 0) {
            die();
            return true;
        }
        return false;
    }

    public void move() {
        // Если мертв или уже без локации - не двигаемся
        if (!alive || location == null) return;

        // 1. Случайное направление и дистанция
        int dx = ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);
        int dy = ThreadLocalRandom.current().nextInt(-getMoveDistance(), getMoveDistance() + 1);

        int newX = this.x + dx;
        int newY = this.y + dy;

        // 2. Проверка границ острова
        if (newX < 0 || newX >= Configuration.X || newY < 0 || newY >= Configuration.Y) {
            return; // Остаёмся на месте
        }

        // 3. Получаем целевую локацию
        Location targetLocation = com.javarush.pavlochev.island.model.Island.getInstance().getLocation(newX, newY);

        // 4. Проверяем, есть ли место в целевой локации
        if (targetLocation.isMaxFor(this)) {
            return; // Остаёмся на месте
        }

        // 5. Перемещаемся (сначала убираем со старой, потом добавляем на новую)
        this.location.removeAnimal(this);
        targetLocation.addAnimal(this);

        this.location = targetLocation;
        this.x = newX;
        this.y = newY;
    }

    protected abstract int getMaxPerLocation();
    protected abstract int getMoveDistance();
}