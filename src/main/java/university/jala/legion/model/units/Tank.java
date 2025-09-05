package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Tank unit in the simulation.
 * This class defines the specific attributes of a Tank, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Tank extends Character {
    private static final int RANK = 2;

    /**
     * Constructs a new Tank unit with the given symbol and numeric value.
     *
     * @param symbol       The character symbol for display.
     * @param numericValue The numeric identifier for the unit's type.
     */
    public Tank(char symbol, int numericValue) {
        super(RANK, symbol, numericValue);
    }

    /**
     * Gets the type of the character.
     * @return The string "Tank".
     */
    @Override
    public String getType() {
        return "Tank";
    }
}
