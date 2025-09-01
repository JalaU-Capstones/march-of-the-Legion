# Project Class Diagrams

This document contains the class diagrams for the **March of the Legion** project, illustrating its architecture based on SOLID principles.

**Note:** For the diagrams to display correctly, the placeholder URL in the image links must be updated to point to your public GitHub repository.

---

## 1. High-Level Simulation Orchestrator

This diagram provides a top-level view of the application's workflow, showing how the `Main` class initiates the `Simulation` orchestrator, which in turn coordinates all the major components of the system.

![Simulation Orchestrator](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/JalaU-Capstones/march-of-the-Legion/main/Diagrams/simulation_orchestrator.puml)

---

## 2. CLI and Validation Architecture

This diagram shows how command-line parameters are parsed and validated. The design uses the **Strategy** and **Factory** patterns to create a flexible and extensible validation system, adhering to the **Single Responsibility Principle (SRP)** and the **Open/Closed Principle (OCP)**.

![CLI and Validation Architecture](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/JalaU-Capstones/march-of-the-Legion/main/Diagrams/cli_parameters_validation.puml)

---

## 3. Core Domain Model Architecture

This diagram illustrates the core domain model. It follows the **Interface Segregation Principle (ISP)** by defining fine-grained interfaces (`Positionable`, `Displayable`) and the **Dependency Inversion Principle (DIP)** by ensuring that high-level components depend on the `ICharacter` abstraction, not on concrete unit classes. The **Factory Pattern** is used to decouple unit creation from the client.

![Domain Model Architecture](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/JalaU-Capstones/march-of-the-Legion/main/Diagrams/model_architecture.puml)

---

## 4. Sorting and Placement Architecture

This diagram shows the orchestration of sorting and placement logic. The `TroopArranger` acts as a coordinator, using the **Strategy Pattern** for both sorting and placement. This design adheres to **SRP** by separating sorting from placement and **OCP** by allowing new algorithms to be added via factories without modifying the client code.

![Sorting and Placement Architecture](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/JalaU-Capstones/march-of-the-Legion/main/Diagrams/sorting_placement_architecture.puml)

---

## 5. Battlefield and Rendering Architecture

This diagram illustrates the separation of battlefield state management from rendering. The `Battlefield` class implements the `IBattlefield` interface, which provides a read-only contract for the rendering components. This design follows **SRP** and **DIP**, as the renderers depend on an abstraction, not on the concrete `Battlefield` class.

![Battlefield and Rendering Architecture](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/JalaU-Capstones/march-of-the-Legion/main/Diagrams/battlefield_rendering_architecture.puml)
