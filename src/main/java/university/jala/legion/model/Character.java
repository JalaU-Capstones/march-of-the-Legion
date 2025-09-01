package university.jala.legion.model;

import university.jala.legion.model.interfaces.ICharacter;

/**
 * Provides a foundational implementation for all military units in the simulation.
 * This abstract class implements the {@link ICharacter} interface and defines the
 * common attributes and behaviors shared by all character types, such as rank, position,
 * and visual representation.
 */
public abstract class Character implements ICharacter {
    private final int rank;
    private Position position;
    private final char symbol;
    private final int numericRange;

    /**
     * Constructs a new Character with the specified attributes.
     *
     * @param rank         The hierarchical rank of the unit.
     * @param symbol       The character symbol for display.
     * @param numericRange The numeric identifier for the unit's type.
     */
    protected Character(int rank, char symbol, int numericRange) {
        this.rank = rank;
        this.symbol = symbol;
        this.numericRange = numericRange;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public int getNumericRange() {
        return numericRange;
    }

    /**
     * Returns the string representation of the character, which is its symbol.
     *
     * @return The character's symbol as a string.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
