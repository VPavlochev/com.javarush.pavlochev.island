# com.javarush.pavlochev.island
   JavaRush Module 2 Final Project - Island Simulation

# Island Simulation - Многопоточная симуляция экосистемы (JavaRush Module 2)
Многопоточная симуляция экосистемы острова с 15 видами животных, реализованная на Java 21.

# Описание
Проект симулирует жизнь на острове размером 100×20 клеток. Каждый игровой день животные:
- Размножаются (требуется партнёр того же вида)
- Перемещаются между локациями
- Охотятся (хищники) или питаются растениями (травоядные)
- Умирают от голода

# Архитектура
Main → ScheduledExecutorService → DayRunner → ExecutorService → AnimalLifeCycleTask


# Ключевые компоненты:

- **`Island`** — синглтон, матрица 100×20 локаций
- **`Location`** — клетка острова, потокобезопасная через `synchronized` + паттерн `UnderLock`
- **`Animal`** — абстрактный базовый класс с уникальным ID, весом, координатами
- **`Predator` / `Herbivore`** — промежуточные абстракции
- **15 видов животных** — 5 хищников + 10 травоядных
- **`Configuration`** — все параметры (максимумы, веса, вероятности)
- **`EatUtil`** — утилита расчёта вероятности поедания
- **Tasks:** `PlantGrowTask`, `AnimalLifeCycleTask`, `StatisticsTask`
- **`DayRunner`** — управляет одним игровым днём

# Виды животных:

# Хищники (5):
| Вид | Макс. на клетку | Начальный вес | Дальность хода |
|-----|----------------|---------------|----------------|
| Wolf | 6 | 50 | 4 |
| Bear | 4 | 300 | 1 |
| Eagle | 4 | 5 | 10 |
| Snake | 8 | 2 | 1 |
| Fox | 6 | 15 | 3 |

# Травоядные (10):
| Вид | Макс. на клетку | Начальный вес | Дальность хода |
|-----|----------------|---------------|----------------|
| Rabbit | 50 | 2 | 2 |
| Horse | 20 | 200 | 4 |
| Mouse | 80 | 0.5 | 1 |
| Deer | 10 | 100 | 3 |
| Sheep | 30 | 50 | 1 |
| Boar | 15 | 80 | 2 |
| Buffalo | 5 | 400 | 1 |
| Goat | 25 | 40 | 2 |
| Duck | 20 | 3 | 3 |
| Caterpillar | 100 | 0.1 | 1 |

# Технологии

- **Java 21**
- **Maven** — сборка проекта
- **SLF4J + Logback** — логирование
- **Jackson** — JSON-конфигурация
- **Lombok** - генерация шаблонного кода
- **Многопоточность:** `ScheduledExecutorService`, `ExecutorService`, `CountDownLatch`, `synchronized`

# Запуск

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.javarush.pavlochev.island.Main"

Или через IntelliJ IDEA: запустить в Main.java.

Ключевые решения:

Потокобезопасность
Location использует synchronized методы + паттерн UnderLock для избежания deadlock
AnimalLifeCycleTask работает со снимком списка животных на начало дня
CountDownLatch синхронизирует статистику после завершения всех задач дня

Размножение
Требуется партнёр того же вида на той же локации
Оба партнёра помечаются как размножившиеся сегодня (lastReproductionDay)
Потомок создаётся через AnimalFactory и добавляется на ту же локацию

Охота
Хищники ищут травоядных на своей локации
Вероятность поедания рассчитывается через EatUtil с параметрами из Configuration
При успешной охоте хищник набирает вес, жертва умирает

Структура проекта:

src/main/java/com/javarush/pavlochev/island/
├── config/
│   ├── Configuration.java
│   └── JsonConfig.java
├── model/
│   ├── animals/ # 15 классов животных
│   ├── base/
│   │   ├── Animal.java
│   │   ├── Herbivore.java
│   │   ├── Organism.java
│   │   └── Predator.java
│   ├── factory/
│   │   └── AnimalFactory.java
│   ├── Island.java
│   ├── Location.java
│   └── Plant.java
├── tasks/
│   ├── AnimalLifeCycleTask.java
│   ├── DayRunner.java
│   ├── PlantGrowTask.java
│   └── StatisticsTask.java
├── util/
│   └── EatUtil.java
└── Main.java
src/main/resources/
├── config.json
└── logback.xml


Автор:
Валерий Павлочев
Java Developer | AI Prompt Engineer
Москва, 23.10.2026 г.
