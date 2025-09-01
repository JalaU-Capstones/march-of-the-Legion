package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.sorting.placement.PlacementStrategy;
import university.jala.legion.sorting.placement.PlacementStrategyFactory;

import java.util.List;

/**
 * Orchestrates the sorting and placement of troops on the battlefield.
 * This class coordinates the use of a sorting strategy to order the units and a
 * placement strategy to assign their final positions.
 */
public class TroopArranger {
    private final SortingStrategy sortingStrategy;
    private final PlacementStrategy placementStrategy;

    /**
     * Constructs a new TroopArranger with the specified strategies.
     *
     * @param sortCode      The code for the desired sorting algorithm ('c', 'r', 'q', 'i').
     * @param orientationCode The code for the desired placement orientation ('n', 's', 'e', 'w').
     */
    public TroopArranger(String sortCode, char orientationCode) {
        this.sortingStrategy = SortingStrategyFactory.create(sortCode);
        this.placementStrategy = PlacementStrategyFactory.create(orientationCode);
    }

    /**
     * Arranges the troops by first sorting them and then placing them on the battlefield.
     *
     * @param units         The list of units to be arranged.
     * @param battlefieldSize The size of the battlefield.
     */
    public void arrange(List<ICharacter> units, int battlefieldSize) {
        sortingStrategy.sort(units);
        placementStrategy.place(units, battlefieldSize);
    }
}
