package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;
import java.util.List;

/**
 * Defines the contract for sorting strategies that arrange military units by rank.
 * This interface allows different sorting algorithms to be used interchangeably.
 */
public interface SortingStrategy {
    /**
     * Sorts the given list of units in place, primarily by their rank.
     *
     * @param units The list of {@link ICharacter} units to be sorted.
     */
    void sort(List<ICharacter> units);
}
