# com.javarush.pavlochev.island
JavaRush Module 2 Final Project - Island Simulation

# Island Simulation — Многопоточная симуляция экосистемы

Многопоточная симуляция экосистемы острова с 15 видами животных, реализованная на Java 21 в рамках 2-го модуля курса JavaRush.

# Описание

Проект симулирует жизнь на острове размером **100×20 клеток**. Каждый игровой день животные:
-  **Размножаются** (требуется партнёр того же вида на той же локации)
- **Перемещаются** между локациями (случайное направление, проверка границ и лимитов)
-  **Охотятся** (хищники) или **питаются растениями** (травоядные)
- **Умирают от голода**, если вес падает до нуля

#  Пример вывода
=== Статистика острова ===
Всего хищников: 1183 🐺
Всего травоядных: 3007 🐇
Всего растений: 200000 🌿
--- Повидовая статистика ---
🐗 Boar: 312
🐴 Horse: 695
🐺 Wolf: 292
🐑 Sheep: 224
🐐 Goat: 387
🐍 Snake: 160
🐃 Buffalo: 17
🐻 Bear: 43
🐭 Mouse: 293
🦊 Fox: 340
🦆 Duck: 288
🦌 Deer: 240
🦅 Eagle: 348
🐇 Rabbit: 551


# Архитектура
Main → ScheduledExecutorService → DayRunner → ExecutorService → AnimalLifeCycleTask
↓
CountDownLatch
↓
StatisticsTask

# Ключевые компоненты:

| Компонент | Назначение |
|-----------|-----------|
| `Island` | Синглтон, матрица 100×20 локаций |
| `Location` | Клетка острова, потокобезопасная через `synchronized` + паттерн `UnderLock` |
| `Animal` | Абстрактный базовый класс с уникальным ID, весом, координатами |
| `Predator` / `Herbivore` | Промежуточные абстракции для хищников и травоядных |
| `Configuration` | Все параметры (максимумы, веса, вероятности, эмодзи) |
| `EatUtil` | Утилита расчёта вероятности поедания |
| `AnimalFactory` | Фабрика для создания животных через рефлексию |
| `DayRunner` | Управляет одним игровым днём |

# Задачи (Tasks):
- **`PlantGrowTask`** — рост растений на локациях
- **`AnimalLifeCycleTask`** — жизненный цикл животных (охота → размножение → перемещение → голод)
- **`StatisticsTask`** — подсчёт и вывод статистики с эмодзи

# Виды животных

# Хищники (5):
| Эмодзи | Вид | Макс. на клетку | Начальный вес | Макс. вес | Дальность хода |
|--------|-----|----------------|---------------|-----------|----------------|
| 🐺 | Wolf | 6 | 50 | 100 | 4 |
| 🐻 | Bear | 4 | 300 | 600 | 1 |
| 🦅 | Eagle | 4 | 5 | 10 | 10 |
| 🐍 | Snake | 8 | 2 | 4 | 1 |
| 🦊 | Fox | 6 | 15 | 30 | 3 |

# Травоядные (10):
| Эмодзи | Вид | Макс. на клетку | Начальный вес | Макс. вес | Дальность хода |
|--------|-----|----------------|---------------|-----------|----------------|
| 🐇 | Rabbit | 50 | 2 | 4 | 2 |
| 🐴 | Horse | 20 | 200 | 400 | 4 |
| 🐭 | Mouse | 80 | 0.5 | 1 | 1 |
| 🦌 | Deer | 10 | 100 | 200 | 3 |
| 🐑 | Sheep | 30 | 50 | 100 | 1 |
| 🐗 | Boar | 15 | 80 | 160 | 2 |
| 🐃 | Buffalo | 5 | 400 | 800 | 1 |
| 🐐 | Goat | 25 | 40 | 80 | 2 |
| 🦆 | Duck | 20 | 3 | 6 | 3 |
| 🐛 | Caterpillar | 100 | 0.1 | 0.2 | 1 |

# Технологии

- **Java 21** (records, switch expressions, pattern matching)
- **Maven** — сборка проекта
- **SLF4J + Logback** — логирование
- **Jackson** — JSON-конфигурация
- **Lombok** — сокращение и генерация шаблонного кода
- **Многопоточность:** `ScheduledExecutorService`, `ExecutorService`, `CountDownLatch`, `synchronized`, `AtomicInteger`

# Ключевые решения
Потокобезопасность
Location использует synchronized методы + паттерн UnderLock для избежания deadlock
AnimalLifeCycleTask работает со снимком списка животных на начало дня
CountDownLatch синхронизирует статистику после завершения всех задач дня
Уникальные ID животных генерируются через AtomicInteger

Размножение
Требуется партнёр того же вида на той же локации
Оба партнёра помечаются как размножившиеся сегодня (lastReproductionDay)
Потомок создаётся через AnimalFactory и добавляется на ту же локацию

Охота
Хищники ищут травоядных на своей локации
Вероятность поедания рассчитывается через EatUtil с параметрами из Configuration
При успешной охоте хищник набирает вес, жертва умирает

Псевдо-графика
Каждому виду животного сопоставлен эмодзи в Configuration
Класс AnimalEmoji возвращает эмодзи по классу животного
Статистика выводится с иконками для наглядности

# Структура проекта
src/main/java/com/javarush/pavlochev/island/
├── config/
│   ├── Configuration.java      # Все параметры + эмодзи
│   └── JsonConfig.java         # JSON-конфигурация (синглтон)
├── model/
│   ├── animals/                # 15 классов животных
│   ├── base/
│   │   ├── Animal.java         # Базовый класс + ID + UnderLock
│   │   ├── Herbivore.java
│   │   ├── Organism.java
│   │   └── Predator.java       # Метод hunt()
│   ├── factory/
│   │   ── AnimalFactory.java  # Создание через рефлексию
│   ├── Island.java             # Синглтон
│   ├── Location.java           # Потокобезопасная клетка
│   ── Plant.java
├── tasks/
│   ├── AnimalLifeCycleTask.java
│   ├── DayRunner.java
│   ├── PlantGrowTask.java
│   └── StatisticsTask.java
├── util/
│   ├── EatUtil.java            # Вероятности поедания
│   └── AnimalEmoji.java        # Эмодзи для видов
└── Main.java
src/main/resources/
├── config.json                 # JSON-конфигурация
└── logback.xml                 # Настройки логгера

# Запуск

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.javarush.pavlochev.island.Main"
Или через IntelliJ IDEA: запустить в Main.java.

Автор;
Валерий Павлочев
Java Developer | AI Prompt Engineer
Москва, 23.10.2026



