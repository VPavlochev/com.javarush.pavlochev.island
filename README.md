# com.javarush.pavlochev.island
   JavaRush Module 2 Final Project - Island Simulation

# Island Simulation - JavaRush Module 2

Симуляция экосистемы острова. Модель острова с изменяемыми параметрами, состоящая из массива локаций, заполненных растительностью и животными.

## Реализовано (Model core - Простой старт)

- Иерархия классов: `Organism` → `Animal` → `Predator`/`Herbivore` → `Wolf`/`Bear`/`Rabbit`/`Horse`
- Класс `Plant` (растительность)
- Класс `Location` (локация с коллекциями животных и растений)
- `EatUtil` — утилитарный класс расчёта вероятности поедания через `ThreadLocalRandom`
- `Configuration` — параметры симуляции (размеры острова, максимумы особей, вероятности)
- Игровой цикл с break через 3 секунды
- Методы: `eat()`, `multiply()`, `die()`, `tick()`

## Запуск

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.javarush.pavlochev.island.Main"
