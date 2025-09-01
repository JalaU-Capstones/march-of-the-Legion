package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents an Infantry unit in the simulation.
 * This class defines the specific attributes of an Infantry unit, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Infantry extends Character {
    private static final int RANK = 4;
    private static final char SYMBOL = 'I';
    private static final int NUMERIC_RANGE = 5;

    /**
     * Constructs a new Infantry unit with predefined attributes.
     */
    public Infantry() {
        super(RANK, SYMBOL, NUMERIC_RANGE);
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
