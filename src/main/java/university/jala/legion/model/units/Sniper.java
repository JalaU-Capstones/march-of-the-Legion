package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Sniper unit in the simulation.
 * This class defines the specific attributes of a Sniper, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Sniper extends Character {
    private static final int RANK = 3;
    private static final char SYMBOL = 'S';
    private static final int NUMERIC_RANGE = 4;

    /**
     * Constructs a new Sniper unit with predefined attributes.
     */
    public Sniper() {
        super(RANK, SYMBOL, NUMERIC_RANGE);
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
