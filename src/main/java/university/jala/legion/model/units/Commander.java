package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Commander unit in the simulation.
 * This class defines the specific attributes of a Commander, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Commander extends Character {
    private static final int RANK = 0;
    private static final char SYMBOL = 'C';
    private static final int NUMERIC_RANGE = 1;

    /**
     * Constructs a new Commander unit with predefined attributes.
     */
    public Commander() {
        super(RANK, SYMBOL, NUMERIC_RANGE);
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
