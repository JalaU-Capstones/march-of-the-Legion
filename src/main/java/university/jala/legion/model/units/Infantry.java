package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents an Infantry unit in the simulation.
 * This class defines the specific attributes of an Infantry unit, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Infantry extends Character {
    private static final int RANK = 4;

    /**
     * Constructs a new Infantry unit with the given symbol and numeric value.
     *
     * @param symbol       The character symbol for display.
     * @param numericValue The numeric identifier for the unit's type.
     */
    public Infantry(char symbol, int numericValue) {
        super(RANK, symbol, numericValue);
    }

    /**
     * Gets the type of the character.
     * @return The string "Infantry".
     */
    @Override
    public String getType() {
        return "Infantry";
    }
}
