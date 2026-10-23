package com.javarush.pavlochev.island.model;

import com.javarush.pavlochev.island.config.Configuration;

// Класс острова — содержит матрицу локаций.
// Синглтон: один остров на всю игру.

public class Island {
    private static final Island INSTANCE = new Island();

    private final Location[][] locations;

    private Island() {
        locations = new Location[Configuration.X][Configuration.Y];
        for (int i = 0; i < Configuration.X; i++) {
            for (int j = 0; j < Configuration.Y; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public static Island getInstance() {
        return INSTANCE;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public Location getLocation(int x, int y) {
        return locations[x][y];
    }
}