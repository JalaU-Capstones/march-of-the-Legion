package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Sniper unit in the simulation.
 * This class defines the specific attributes of a Sniper, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Sniper extends Character {
    private static final int RANK = 3;

    /**
     * Constructs a new Sniper unit with the given symbol and numeric value.
     *
     * @param symbol       The character symbol for display.
     * @param numericValue The numeric identifier for the unit's type.
     */
    public Sniper(char symbol, int numericValue) {
        super(RANK, symbol, numericValue);
    }

    /**
     * Gets the type of the character.
     * @return The string "Sniper".
     */
    @Override
    public String getType() {
        return "Sniper";
    }
}
