package university.jala.legion.sorting.placement;

import university.jala.legion.model.Position;
import university.jala.legion.model.interfaces.ICharacter;

import java.util.List;

/**
 * Places units on the battlefield starting from the east and moving west.
 * Units are arranged column by column, from top to bottom. If a group of units
 * of the same type fills a column, it wraps to the previous available column.
 */
public class WestPlacementStrategy implements PlacementStrategy {
    @Override
    public void place(List<ICharacter> units, int battlefieldSize) {
        if (units.isEmpty()) {
            return;
        }

        int currentCol = battlefieldSize - 1;
        int currentRow = 0;
        int currentRank = units.get(0).getRank();

        for (ICharacter unit : units) {
            // If rank changes, start a new column for the new group.
            if (unit.getRank() != currentRank) {
                currentCol--;
                currentRow = 0;
                currentRank = unit.getRank();
            }

            // If the current column is full, wrap to the previous column for the same group.
            if (currentRow >= battlefieldSize) {
                currentCol--;
                currentRow = 0;
            }

            // Check if we've run out of battlefield space.
            if (currentCol < 0) {
                throw new IllegalArgumentException("Not enough space on the battlefield for all units.");
            }

            unit.setPosition(new Position(currentRow, currentCol));
            currentRow++;
        }
    }
}
