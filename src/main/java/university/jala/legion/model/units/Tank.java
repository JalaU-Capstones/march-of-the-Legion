package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Tank unit in the simulation.
 * This class defines the specific attributes of a Tank, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Tank extends Character {
    private static final int RANK = 2;
    private static final char SYMBOL = 'T';
    private static final int NUMERIC_RANGE = 3;

    /**
     * Constructs a new Tank unit with predefined attributes.
     */
    public Tank() {
        super(RANK, SYMBOL, NUMERIC_RANGE);
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
