package university.jala.legion.model.interfaces;

/**
 * Defines the contract for a read-only view of the battlefield.
 * This interface provides essential information about the battlefield's state,
 * such as its size and the units on it, without exposing modification methods.
 */
public interface IBattlefield {
    /**
     * Gets the size of the battlefield (for an N x N grid).
     *
     * @return The size of one dimension of the battlefield.
     */
    int getSize();

    /**
     * Retrieves the character at a specific position on the grid.
     *
     * @param row    The row of the position.
     * @param column The column of the position.
     * @return The {@link ICharacter} at the given position, or null if it's empty.
     */
    ICharacter getUnitAt(int row, int column);
}
