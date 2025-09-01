package university.jala.legion.sorting.placement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.exception.SimulationException;
import university.jala.legion.model.Position;
import university.jala.legion.model.interfaces.ICharacter;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link EastPlacementStrategy} class.
 */
class EastPlacementStrategyTest {

    private EastPlacementStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new EastPlacementStrategy();
    }

    private ICharacter createMockUnit(int rank) {
        ICharacter unit = Mockito.mock(ICharacter.class);
        Mockito.when(unit.getRank()).thenReturn(rank);
        return unit;
    }

    @Test
    void testPlaceUnitsInEastDirection() throws SimulationException {
        // It should place units from west to east, column by column for each rank.
        List<ICharacter> units = new ArrayList<>();
        ICharacter commander = createMockUnit(0);
        ICharacter medic1 = createMockUnit(1);
        ICharacter medic2 = createMockUnit(1);
        units.add(commander);
        units.add(medic1);
        units.add(medic2);

        strategy.place(units, 10);

        // Verify that setPosition was called with the correct coordinates for each unit.
        verify(commander).setPosition(new Position(0, 0));
        verify(medic1).setPosition(new Position(0, 1));
        verify(medic2).setPosition(new Position(1, 1));
    }

    @Test
    void testNotEnoughColumnsForUnitsThrowsException() {
        // It should throw a SimulationException if there are not enough columns for all ranks.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(0));
        units.add(createMockUnit(1));

        SimulationException exception = assertThrows(SimulationException.class, () -> {
            strategy.place(units, 1); // Only one column available
        });

        assertEquals("Not enough space on the battlefield to place all units.", exception.getMessage());
    }

    @Test
    void testNotEnoughSpaceWhenWrappingThrowsException() {
        // It should throw a SimulationException if a rank group wraps but still runs out of space.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(0));
        units.add(createMockUnit(0));

        SimulationException exception = assertThrows(SimulationException.class, () -> {
            strategy.place(units, 1); // Only one row available, forcing a wrap that fails
        });

        assertEquals("Not enough space on the battlefield to place all units.", exception.getMessage());
    }

    @Test
    void testEmptyUnitListDoesNotThrow() throws SimulationException {
        // It should not throw an exception when the unit list is empty.
        List<ICharacter> units = new ArrayList<>();
        strategy.place(units, 10);
    }
}
