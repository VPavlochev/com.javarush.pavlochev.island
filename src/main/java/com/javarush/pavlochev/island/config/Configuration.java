package com.javarush.pavlochev.island.config;

// Конфигурация симуляции острова.
// Все параметры вынесены сюда для централизованного управления.

public final class Configuration {
    private Configuration() {}

    // Размеры острова
    public static final int X = 100;
    public static final int Y = 20;

    // Максимальное количество растений на локации
    public static final int MAX_PLANT = 100;

    // Максимальное количество хищников на локации
    public static final int MAX_WOLF = 2;
    public static final int MAX_BEAR = 1;
    public static final int MAX_EAGLE = 2;
    public static final int MAX_SNAKE = 4;
    public static final int MAX_FOX = 2;

    // Максимальное количество травоядных на локации
    public static final int MAX_RABBIT = 50;
    public static final int MAX_HORSE = 20;
    public static final int MAX_MOUSE = 80;
    public static final int MAX_DEER = 10;
    public static final int MAX_SHEEP = 30;
    public static final int MAX_BOAR = 15;
    public static final int MAX_BUFFALO = 5;
    public static final int MAX_GOAT = 25;
    public static final int MAX_DUCK = 20;
    public static final int MAX_CATERPILLAR = 100;

    // Начальный вес для хищников
    public static final double WOLF_WEIGHT = 50;
    public static final double BEAR_WEIGHT = 300;
    public static final double EAGLE_WEIGHT = 5;
    public static final double SNAKE_WEIGHT = 2;
    public static final double FOX_WEIGHT = 15;

    // Начальный вес для травоядных
    public static final double RABBIT_WEIGHT = 2;
    public static final double HORSE_WEIGHT = 200;
    public static final double MOUSE_WEIGHT = 0.5;
    public static final double DEER_WEIGHT = 100;
    public static final double SHEEP_WEIGHT = 50;
    public static final double BOAR_WEIGHT = 80;
    public static final double BUFFALO_WEIGHT = 400;
    public static final double GOAT_WEIGHT = 40;
    public static final double DUCK_WEIGHT = 3;
    public static final double CATERPILLAR_WEIGHT = 0.1;

    // Максимальный вес для хищников
    public static final double WOLF_MAX_WEIGHT = 100;
    public static final double BEAR_MAX_WEIGHT = 600;
    public static final double EAGLE_MAX_WEIGHT = 10;
    public static final double SNAKE_MAX_WEIGHT = 4;
    public static final double FOX_MAX_WEIGHT = 30;

    // Максимальный вес для травоядных
    public static final double RABBIT_MAX_WEIGHT = 4;
    public static final double HORSE_MAX_WEIGHT = 400;
    public static final double MOUSE_MAX_WEIGHT = 1;
    public static final double DEER_MAX_WEIGHT = 200;
    public static final double SHEEP_MAX_WEIGHT = 100;
    public static final double BOAR_MAX_WEIGHT = 160;
    public static final double BUFFALO_MAX_WEIGHT = 800;
    public static final double GOAT_MAX_WEIGHT = 80;
    public static final double DUCK_MAX_WEIGHT = 6;
    public static final double CATERPILLAR_MAX_WEIGHT = 0.2;

    // Потеря веса за такт (для всех животных)
    public static final double WEIGHT_LOSS_PER_TICK = 1.0;

    // Вероятности поедания хищниками (проценты)
    // Волк
    public static final int WOLF_EATS_RABBIT = 80;
    public static final int WOLF_EATS_HORSE = 20;
    public static final int WOLF_EATS_MOUSE = 90;
    public static final int WOLF_EATS_DEER = 40;
    public static final int WOLF_EATS_SHEEP = 50;
    public static final int WOLF_EATS_BOAR = 30;
    public static final int WOLF_EATS_BUFFALO = 5;
    public static final int WOLF_EATS_GOAT = 60;
    public static final int WOLF_EATS_DUCK = 70;

    // Медведь
    public static final int BEAR_EATS_RABBIT = 90;
    public static final int BEAR_EATS_HORSE = 30;
    public static final int BEAR_EATS_MOUSE = 95;
    public static final int BEAR_EATS_DEER = 50;
    public static final int BEAR_EATS_SHEEP = 60;
    public static final int BEAR_EATS_BOAR = 40;
    public static final int BEAR_EATS_BUFFALO = 10;
    public static final int BEAR_EATS_GOAT = 70;
    public static final int BEAR_EATS_DUCK = 80;

    // Орёл
    public static final int EAGLE_EATS_RABBIT = 70;
    public static final int EAGLE_EATS_MOUSE = 95;
    public static final int EAGLE_EATS_DUCK = 60;
    public static final int EAGLE_EATS_CATERPILLAR = 90;

    // Змея
    public static final int SNAKE_EATS_RABBIT = 30;
    public static final int SNAKE_EATS_MOUSE = 90;
    public static final int SNAKE_EATS_DUCK = 20;
    public static final int SNAKE_EATS_CATERPILLAR = 80;

    // Лиса
    public static final int FOX_EATS_RABBIT = 85;
    public static final int FOX_EATS_MOUSE = 95;
    public static final int FOX_EATS_DUCK = 75;
    public static final int FOX_EATS_CATERPILLAR = 50;

    // Максимальное расстояние перемещения для каждого вида (в клетках)
    public static final int WOLF_MOVE_DISTANCE = 4;
    public static final int BEAR_MOVE_DISTANCE = 1;
    public static final int EAGLE_MOVE_DISTANCE = 10;
    public static final int SNAKE_MOVE_DISTANCE = 1;
    public static final int FOX_MOVE_DISTANCE = 3;

    public static final int RABBIT_MOVE_DISTANCE = 2;
    public static final int HORSE_MOVE_DISTANCE = 4;
    public static final int MOUSE_MOVE_DISTANCE = 1;
    public static final int DEER_MOVE_DISTANCE = 3;
    public static final int SHEEP_MOVE_DISTANCE = 1;
    public static final int BOAR_MOVE_DISTANCE = 2;
    public static final int BUFFALO_MOVE_DISTANCE = 1;
    public static final int GOAT_MOVE_DISTANCE = 2;
    public static final int DUCK_MOVE_DISTANCE = 3;
    public static final int CATERPILLAR_MOVE_DISTANCE = 1;
}