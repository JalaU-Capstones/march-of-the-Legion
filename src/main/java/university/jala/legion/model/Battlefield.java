package university.jala.legion.model;

import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents the battlefield grid and manages the state of units upon it.
 * This class is responsible for maintaining the grid and the list of units,
 * but delegates rendering and placement logic to other components.
 */
public class Battlefield implements IBattlefield {
    private final int size;
    private final ICharacter[][] grid;
    private final List<ICharacter> units;

    /**
     * Constructs a new Battlefield with a specified size.
     *
     * @param size The size of one dimension of the N x N grid.
     */
    public Battlefield(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Battlefield size must be positive.");
        }
        this.size = size;
        this.grid = new ICharacter[size][size];
        this.units = new ArrayList<>();
    }

    /**
     * Sets the troops on the battlefield, clearing any previous state.
     * The positions of the units in the list are used to place them on the grid.
     *
     * @param troops The list of troops to place on the battlefield.
     */
    public void setUnits(List<ICharacter> troops) {
        clear();
        this.units.addAll(troops);
        for (ICharacter unit : this.units) {
            if (unit.getPosition() != null) {
                placeUnitOnGrid(unit);
            }
        }
    }

    private void placeUnitOnGrid(ICharacter unit) {
        Position position = unit.getPosition();
        if (position.row() >= 0 && position.row() < size &&
            position.column() >= 0 && position.column() < size) {
            if (grid[position.row()][position.column()] == null) {
                grid[position.row()][position.column()] = unit;
            } else {
                // Handle position conflict if necessary, e.g., throw an exception
            }
        }
    }

    /**
     * Clears the battlefield, removing all units and resetting the grid.
     */
    public void clear() {
        this.units.clear();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public ICharacter getUnitAt(int row, int column) {
        if (row >= 0 && row < size && column >= 0 && column < size) {
            return grid[row][column];
        }
        return null;
    }

    /**
     * Returns an unmodifiable view of the list of units on the battlefield.
     *
     * @return An unmodifiable list of units.
     */
    public List<ICharacter> getUnits() {
        return Collections.unmodifiableList(units);
    }
}
