package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Commander unit in the simulation.
 * This class defines the specific attributes of a Commander, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Commander extends Character {
    private static final int RANK = 0;

    /**
     * Constructs a new Commander unit with the given symbol and numeric value.
     *
     * @param symbol       The character symbol for display.
     * @param numericValue The numeric identifier for the unit's type.
     */
    public Commander(char symbol, int numericValue) {
        super(RANK, symbol, numericValue);
    }

    /**
     * Gets the type of the character.
     * @return The string "Commander".
     */
    @Override
    public String getType() {
        return "Commander";
    }
}
