package university.jala.legion.sorting.placement;

import university.jala.legion.model.interfaces.ICharacter;
import java.util.List;

/**
 * Defines the contract for strategies that place sorted units on the battlefield.
 * This interface allows for different placement algorithms (e.g., by orientation)
 * to be implemented and used interchangeably.
 */
public interface PlacementStrategy {
    /**
     * Assigns a final position to each unit in the list based on a specific algorithm.
     *
     * @param units         The list of units to be placed, assumed to be sorted by rank.
     * @param battlefieldSize The size of the N x N battlefield.
     * @throws IllegalArgumentException if the units cannot fit on the battlefield.
     */
    void place(List<ICharacter> units, int battlefieldSize);
}
