package university.jala.legion.model.enums;

/**
 * Represents the types of military units available in the simulation.
 * Using an enum provides type safety and makes the code more readable and maintainable.
 */
public enum UnitType {
    COMMANDER("commander"),
    MEDIC("medic"),
    TANK("tank"),
    SNIPER("sniper"),
    INFANTRY("infantry");

    private final String name;

    UnitType(String name) {
        this.name = name;
    }

    /**
     * Returns the lowercase name of the unit type, suitable for use in factories or configuration.
     *
     * @return The name of the unit type.
     */
    public String getName() {
        return name;
    }
}
