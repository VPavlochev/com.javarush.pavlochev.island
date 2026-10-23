package com.javarush.pavlochev.island.tasks;

import com.javarush.pavlochev.island.config.Configuration;
import com.javarush.pavlochev.island.model.Location;
import com.javarush.pavlochev.island.model.Plant;

// Задача роста растений на один игровой день.
// Добавляет растения на локации, где их меньше максимума.

public class PlantGrowTask implements Runnable {
    private final Location[][] locations;

    public PlantGrowTask(Location[][] locations) {
        this.locations = locations;
    }

    @Override
    public void run() {
        for (Location[] row : locations) {
            for (Location location : row) {
                int currentPlants = location.getPlants().size();
                int toGrow = Configuration.MAX_PLANT - currentPlants;

                // Добавляем не более 10 растений за такт (постепенный рост)
                int growCount = Math.min(toGrow, 10);

                for (int i = 0; i < growCount; i++) {
                    location.addPlant(new Plant());
                }
            }
        }
    }
}
