# 🎖️ March of the Legion

> A tactical battlefield simulation showcasing sorting algorithms through military unit formations, built on SOLID design principles.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](pom.xml)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 🚀 Overview

**March of the Legion** is a Java application that transforms abstract sorting algorithms into a visual military battlefield simulation. It is designed from the ground up using **SOLID principles** to create a clean, modular, and extensible architecture. Watch as different unit types reorganize themselves across the battlefield using various sorting strategies, providing an engaging way to understand both algorithmic behavior and modern software design patterns.

### ✨ Key Features

- **SOLID Architecture**: A clean, maintainable codebase built on industry-best practices.
- **Immersive Startup**: Features an ASCII art banner and background sound for a better user experience.
- **Descriptive Output**: Displays full algorithm names (e.g., "Quick Sort") for clarity.
- **4 Sorting Algorithms**: Counting Sort, Radix Sort, Quick Sort, and Insertion Sort.
- **5 Military Unit Types**: Commander, Medic, Tank, Sniper, and Infantry.
- **Dynamic Battlefield**: Customizable grid sizes from 5x5 to 1000x1000.

---

## 🏗️ Architectural Design

The project's architecture is strictly based on the five **SOLID principles** to ensure it is robust, maintainable, and easy to extend.

- **Single Responsibility Principle (SRP)**: Each class has a single, well-defined purpose. For example, the `StartupPresenter` handles the banner, while the `BattlefieldRenderer` handles display logic.

- **Open/Closed Principle (OCP)**: The system is open for extension but closed for modification. New features like the startup banner are added in new classes without altering core logic.

- **Liskov Substitution Principle (LSP)**: All concrete implementations are substitutable for their abstractions. For example, any `SortingStrategy` can be used by the `TroopArranger`.

- **Interface Segregation Principle (ISP)**: The design features lean, focused interfaces (e.g., `ICharacter`, `IBattlefield`). This ensures that classes do not depend on methods they don't use.

- **Dependency Inversion Principle (DIP)**: High-level modules depend on abstractions, not on low-level implementations. The `Simulation` class depends on `CliParameters` and `BattlefieldRenderer`.

### 📊 Architectural Diagrams

For a detailed visual overview of the architecture, please see the class diagrams:

➡️ **[View Project Diagrams](./Diagrams/DIAGRAMS.md)**

---

## 🚀 Getting Started

### Prerequisites

- ☕ **Java 21**
- 🔧 **Maven 3.6+**

### Build and Test

```bash
# 1. Clone the repository
git clone https://github.com/JalaU-Capstones/march-of-the-Legion.git
cd march-of-the-Legion

# 2. Clean, compile, test, and package the application
mvn clean package
```

This will produce an executable JAR file in the `target/` directory.

---

## 🎮 How to Run

Run the application from the command line using the executable JAR.

### Command Line Parameters

| Parameter | Description | Values | Required               |
|-----------|-------------|--------|------------------------|
| `a` | Sorting Algorithm | `c`, `r`, `q`, `i`. The output will display the full name. | ✅                      |
| `u` or `r` | Unit Distribution | `commander,medic,tank,sniper,infantry` (comma-separated) | ✅                      |
| `f` | Battlefield Size | `5-1000` (creates an NxN grid) | ❌ (default: 6)         |
| `o` | Formation Orientation | `n`, `s`, `e`, `w` | ❌ (default: North)     |
| `t` | Display Type | `c` (Character), `n` (Numeric) | ❌ (default: Character) |

### Example Command

```bash
java -jar target/legion-1.0-SNAPSHOT.jar a=q u=1,2,5,4,10 f=10 o=s t=c
```

---

## ✨ Extra Features

To enhance the user experience, the following features have been added:

### Startup Banner and Sound
- **ASCII Banner**: At startup, a large ASCII art banner is displayed for 7 seconds.
- **Background Sound**: While the banner is visible, a background sound effect (`background.wav`) is played. If the file is missing, a warning is logged, but the application will not crash.

To change the sound, replace the `background.wav` file in `src/main/resources` with your own `.wav` file.

### State Transition Delay
- **2-Second Pause**: There is a 2-second delay after the initial battlefield position is printed, allowing the user to observe the state before the final, sorted position is shown.

---

## 📜 Changelog

### v1.3.0
- **Added Startup Banner**: The application now starts with a 7-second ASCII art banner.
- **Added Background Sound**: A background sound effect now plays during the startup banner.
- **Added State Delay**: A 2-second delay was added between the initial and final battlefield displays.

### v1.2.0
- **Descriptive Output**: The simulation now prints the full algorithm name (e.g., "Insertion Sort").

### v1.1.0
- **New Alias**: Added `r` as an alias for `u` for defining troop distribution.
- **Robust Error Handling**: The simulation now stops immediately on invalid input, and error messages are colored red.
- **Improved Grid Alignment**: The battlefield grid is now perfectly aligned with dynamic cell widths.

---

## 📂 Project Structure

The project follows a clean, modular structure based on its features:

```
src
├── main
│   ├── java
│   │   └── university/jala/legion
│   │       ├── Main.java
│   │       ├── cli/
│   │       ├── model/
│   │       ├── rendering/
│   │       ├── simulation/
│   │       ├── sorting/
│   │       └── util/           # General-purpose utilities (AnsiColor, StartupPresenter)
│   └── resources
│       └── background.wav      # Background sound file
└── test
    └── java
        └── university/jala/legion
```

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
