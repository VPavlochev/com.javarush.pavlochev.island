package com.javarush.pavlochev.island.config;

public final class Configuration {
    public static final int X = 1;
    public static final int Y = 1;

    public static final int MAX_WOLF = 2;
    public static final int MAX_BEAR = 2;
    public static final int MAX_RABBIT = 4;
    public static final int MAX_HORSE = 4;
    public static final int MAX_PLANT = 10;

    public static final double INITIAL_WEIGHT = 75.0;
    public static final double MAX_WEIGHT_WOLF = 300.0;
    public static final double MAX_WEIGHT_BEAR = 500.0;
    public static final double MAX_WEIGHT_RABBIT = 2.0;
    public static final double MAX_WEIGHT_HORSE = 300.0;
    public static final double WEIGHT_LOSS_PER_TICK = 1.0;

    public static final int WOLF_EATS_RABBIT = 60;
    public static final int WOLF_EATS_HORSE = 10;
    public static final int BEAR_EATS_RABBIT = 80;
    public static final int BEAR_EATS_HORSE = 40;

    private Configuration() {}
}
