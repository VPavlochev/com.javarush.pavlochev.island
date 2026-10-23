package com.javarush.pavlochev.island.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonConfig {
    private static final Logger log = LoggerFactory.getLogger(JsonConfig.class);
    private static JsonConfig instance;
    private ConfigData data;

    private JsonConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.json")) {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(is, ConfigData.class);
            log.info("JSON конфигурация загружена успешно");
        } catch (Exception e) {
            log.error("Ошибка загрузки JSON конфигурации", e);
            throw new RuntimeException("Не удалось загрузить config.json", e);
        }
    }

    public static JsonConfig getInstance() {
        if (instance == null) {
            instance = new JsonConfig();
        }
        return instance;
    }

    public ConfigData getData() {
        return data;
    }

    // Внутренние классы для маппинга JSON
    public static class ConfigData {
        public IslandSize islandSize;
        public int maxPlantsPerLocation;
        public java.util.Map<String, AnimalConfig> predators;
        public java.util.Map<String, AnimalConfig> herbivores;
    }

    public static class IslandSize {
        public int x;
        public int y;
    }

    public static class AnimalConfig {
        public int maxPerLocation;
        public double initialWeight;
        public double maxWeight;
        public int moveDistance;
    }
}