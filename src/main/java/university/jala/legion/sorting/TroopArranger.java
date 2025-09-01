package university.jala.legion.sorting;

import university.jala.legion.exception.SimulationException;
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
        this(SortingStrategyFactory.create(sortCode), PlacementStrategyFactory.create(orientationCode));
    }

    /**
     * Package-private constructor for testing purposes, allowing strategy injection.
     */
    TroopArranger(SortingStrategy sortingStrategy, PlacementStrategy placementStrategy) {
        this.sortingStrategy = sortingStrategy;
        this.placementStrategy = placementStrategy;
    }

    /**
     * Arranges the troops by first sorting them and then placing them on the battlefield.
     *
     * @param units         The list of units to be arranged.
     * @param battlefieldSize The size of the battlefield.
     * @throws SimulationException if the placement strategy fails to place the units.
     */
    public void arrange(List<ICharacter> units, int battlefieldSize) throws SimulationException {
        sortingStrategy.sort(units);
        placementStrategy.place(units, battlefieldSize);
    }
}
