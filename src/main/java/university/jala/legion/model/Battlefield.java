package university.jala.legion.model;

import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the battlefield grid and manages the state of units upon it.
 * This class is responsible for maintaining the grid, the list of units,
 * and handling unit placements.
 */
public class Battlefield implements IBattlefield {
    private final int size;
    private final ICharacter[][] grid;
    private final List<ICharacter> units;
    private final Random random = new Random();

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
     * Places a list of units at random, non-overlapping positions on the battlefield.
     *
     * @param unitsToPlace The list of units to be placed.
     * @throws IllegalArgumentException if the number of units exceeds the battlefield capacity.
     */
    public void placeUnitsRandomly(List<ICharacter> unitsToPlace) {
        if (unitsToPlace.size() > size * size) {
            throw new IllegalArgumentException("Number of units exceeds battlefield capacity.");
        }

        clear();

        List<Integer> availableSlots = IntStream.range(0, size * size).boxed().collect(Collectors.toList());
        Collections.shuffle(availableSlots, random);

        for (int i = 0; i < unitsToPlace.size(); i++) {
            ICharacter unit = unitsToPlace.get(i);
            int slot = availableSlots.get(i);
            Position newPosition = new Position(slot / size, slot % size);
            unit.setPosition(newPosition);
            placeUnit(unit);
        }
    }

    /**
     * Sets the troops on the battlefield according to their predefined positions, clearing any previous state.
     * This is typically used after sorting to place units in their final formation.
     *
     * @param troops The list of troops to place on the battlefield.
     */
    public void setUnits(List<ICharacter> troops) {
        clear();
        for (ICharacter unit : troops) {
            if (unit.getPosition() != null) {
                placeUnit(unit);
            }
        }
    }

    /**
     * Places a single unit on the grid and adds it to the internal list of units.
     *
     * @param unit The unit to place.
     * @throws IllegalStateException if the target position is already occupied.
     */
    private void placeUnit(ICharacter unit) {
        Position position = unit.getPosition();
        if (isWithinBounds(position)) {
            if (grid[position.row()][position.column()] == null) {
                grid[position.row()][position.column()] = unit;
                this.units.add(unit);
            } else {
                throw new IllegalStateException("Position " + position + " is already occupied.");
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

    private boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < size &&
               position.column() >= 0 && position.column() < size;
    }
}
