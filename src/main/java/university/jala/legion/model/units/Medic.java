package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Medic unit in the simulation.
 * This class defines the specific attributes of a Medic, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Medic extends Character {
    private static final int RANK = 1;

    /**
     * Constructs a new Medic unit with the given symbol and numeric value.
     *
     * @param symbol       The character symbol for display.
     * @param numericValue The numeric identifier for the unit's type.
     */
    public Medic(char symbol, int numericValue) {
        super(RANK, symbol, numericValue);
    }

    /**
     * Gets the type of the character.
     * @return The string "Medic".
     */
    @Override
    public String getType() {
        return "Medic";
    }
}
