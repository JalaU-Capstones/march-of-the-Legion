package university.jala.legion.sorting.placement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.model.Position;
import university.jala.legion.model.interfaces.ICharacter;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link SouthPlacementStrategy} class.
 */
class SouthPlacementStrategyTest {

    private SouthPlacementStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new SouthPlacementStrategy();
    }

    private ICharacter createMockUnit(int rank) {
        ICharacter unit = Mockito.mock(ICharacter.class);
        Mockito.when(unit.getRank()).thenReturn(rank);
        return unit;
    }

    @Test
    void testPlaceUnitsInSouthDirection() {
        // It should place units from north to south, row by row for each rank.
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
        verify(medic1).setPosition(new Position(1, 0));
        verify(medic2).setPosition(new Position(1, 1));
    }

    @Test
    void testNotEnoughRowsForUnitsThrowsException() {
        // It should throw an exception if there are not enough rows for all ranks.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(0));
        units.add(createMockUnit(1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.place(units, 1); // Only one row available
        });

        assertEquals("Not enough space on the battlefield for all units.", exception.getMessage());
    }

    @Test
    void testNotEnoughSpaceWhenWrappingThrowsException() {
        // It should throw an exception if a rank group wraps but still runs out of space.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(0));
        units.add(createMockUnit(0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.place(units, 1); // Only one column available, forcing a wrap that fails
        });

        assertEquals("Not enough space on the battlefield for all units.", exception.getMessage());
    }

    @Test
    void testEmptyUnitList() {
        // It should not throw an exception when the unit list is empty.
        List<ICharacter> units = new ArrayList<>();
        assertDoesNotThrow(() -> strategy.place(units, 10));
    }
}
