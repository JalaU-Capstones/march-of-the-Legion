# 🎖️ March of the Legion

> A tactical battlefield simulation showcasing sorting algorithms through military unit formations, built on SOLID design principles.

[![Java](https://img.shields.io/badge/Java-24-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](pom.xml)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 🚀 Overview

**March of the Legion** is a Java application that transforms abstract sorting algorithms into a visual military battlefield simulation. It is designed from the ground up using **SOLID principles** to create a clean, modular, and extensible architecture. Watch as different unit types reorganize themselves across the battlefield using various sorting strategies, providing an engaging way to understand both algorithmic behavior and modern software design patterns.

### ✨ Key Features

- **SOLID Architecture**: A clean, maintainable codebase built on industry-best practices.
- **4 Sorting Algorithms**: Counting Sort, Radix Sort, Quick Sort, and Insertion Sort.
- **5 Military Unit Types**: Commander, Medic, Tank, Sniper, and Infantry.
- **Dynamic Battlefield**: Customizable grid sizes from 5x5 to 1000x1000.
- **4 Formation Orientations**: North, South, East, and West deployment patterns.
- **Dual Display Modes**: Render the battlefield with character symbols or numeric ranges.
- **Comprehensive Test Suite**: High test coverage with JUnit 5 and Mockito.

---

## 🏗️ Architectural Design

The project's architecture is strictly based on the five **SOLID principles** to ensure it is robust, maintainable, and easy to extend.

- **Single Responsibility Principle (SRP)**: Each class has a single, well-defined purpose. For example, the `Battlefield` class manages state, while the `BattlefieldRenderer` handles display logic. Validators, sorters, and placement strategies all have their own distinct responsibilities.

- **Open/Closed Principle (OCP)**: The system is open for extension but closed for modification. New sorting algorithms, placement strategies, or renderers can be added by creating new classes and updating a factory, without changing any existing code.

- **Liskov Substitution Principle (LSP)**: All concrete implementations are substitutable for their abstractions. For example, any `SortingStrategy` can be used by the `TroopArranger` without altering its correctness.

- **Interface Segregation Principle (ISP)**: The design features lean, focused interfaces (e.g., `ICharacter`, `IBattlefield`, `Positionable`). This ensures that classes do not depend on methods they don't use.

- **Dependency Inversion Principle (DIP)**: High-level modules depend on abstractions, not on low-level implementations. The `Simulation` class depends on interfaces like `CliParameters` and `BattlefieldRenderer`, not on their concrete classes.

### 📊 Architectural Diagrams

For a detailed visual overview of the architecture, please see the class diagrams:

➡️ **[View Project Diagrams](./Diagrams/DIAGRAMS.md)**

---

## 🚀 Getting Started

### Prerequisites

- ☕ **Java 21**
- 🔧 **Maven 3.6+**

### Build and Test

Follow these steps to build the project and run the full test suite from your terminal.

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

After packaging the application, you can run it directly from the command line.

### Command Line Parameters

| Parameter | Description | Values | Required |
|-----------|-------------|--------|----------|
| `a` | Sorting Algorithm | `c` (Counting), `r` (Radix), `q` (Quick), `i` (Insertion) | ✅ |
| `u` | Unit Distribution | `commander,medic,tank,sniper,infantry` (comma-separated) | ✅ |
| `f` | Battlefield Size | `5-1000` (creates an NxN grid) | ❌ (default: 10) |
| `o` | Formation Orientation | `n` (North), `s` (South), `e` (East), `w` (West) | ❌ (default: North) |
| `t` | Display Type | `c` (Character), `n` (Numeric) | ❌ (default: Character) |

### Example Command

Use the following format to run the simulation from the executable JAR:

```bash
java -jar target/legion-1.0-SNAPSHOT.jar a=q u=1,2,5,4,10 f=10 o=s t=c
```

---

## 📂 Project Structure

The project follows a clean, modular structure based on its features:

```
src
├── main
│   └── java
│       └── university/jala/legion
│           ├── Main.java
│           ├── cli/            # Parameter parsing and validation
│           ├── model/          # Core domain objects and interfaces
│           ├── rendering/      # Battlefield rendering strategies
│           ├── simulation/     # Main simulation orchestrator
│           └── sorting/        # Sorting and placement strategies
└── test
    └── java
        └── university/jala/legion # Unit and integration tests
```

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
