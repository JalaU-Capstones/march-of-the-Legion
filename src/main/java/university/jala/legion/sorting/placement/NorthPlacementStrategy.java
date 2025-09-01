package university.jala.legion.sorting.placement;

import university.jala.legion.model.Position;
import university.jala.legion.model.interfaces.ICharacter;

import java.util.List;

/**
 * Places units on the battlefield starting from the south and moving north.
 * Units are arranged row by row, from left to right. If a group of units
 * of the same type fills a row, it wraps to the next available row.
 */
public class NorthPlacementStrategy implements PlacementStrategy {
    @Override
    public void place(List<ICharacter> units, int battlefieldSize) {
        if (units.isEmpty()) {
            return;
        }

        int currentRow = battlefieldSize - 1;
        int currentCol = 0;
        int currentRank = units.get(0).getRank();

        for (ICharacter unit : units) {
            // If rank changes, start a new row for the new group.
            if (unit.getRank() != currentRank) {
                currentRow--;
                currentCol = 0;
                currentRank = unit.getRank();
            }

            // If the current row is full, wrap to the next row for the same group.
            if (currentCol >= battlefieldSize) {
                currentRow--;
                currentCol = 0;
            }

            // Check if we've run out of battlefield space.
            if (currentRow < 0) {
                throw new IllegalArgumentException("Not enough space on the battlefield for all units.");
            }

            unit.setPosition(new Position(currentRow, currentCol));
            currentCol++;
        }
    }
}
