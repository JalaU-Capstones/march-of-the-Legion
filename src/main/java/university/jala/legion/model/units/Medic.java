package university.jala.legion.model.units;

import university.jala.legion.model.Character;

/**
 * Represents a Medic unit in the simulation.
 * This class defines the specific attributes of a Medic, including its rank,
 * display symbol, and numeric range, as part of the military hierarchy.
 */
public class Medic extends Character {
    private static final int RANK = 1;
    private static final char SYMBOL = 'M';
    private static final int NUMERIC_RANGE = 2;

    /**
     * Constructs a new Medic unit with predefined attributes.
     */
    public Medic() {
        super(RANK, SYMBOL, NUMERIC_RANGE);
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
