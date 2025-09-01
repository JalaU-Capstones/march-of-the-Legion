package university.jala.legion.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import university.jala.legion.model.interfaces.ICharacter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Battlefield} class.
 */
class BattlefieldTest {

    private Battlefield battlefield;

    @BeforeEach
    void setUp() {
        battlefield = new Battlefield(10);
    }

    @Test
    void testBattlefieldInitialization() {
        // It should initialize with the correct size and an empty grid.
        assertEquals(10, battlefield.getSize());
        assertTrue(battlefield.getUnits().isEmpty());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertNull(battlefield.getUnitAt(i, j));
            }
        }
    }

    @Test
    void testSetUnitsPlacesUnitsOnGrid() {
        // It should correctly place units on the grid based on their position.
        ICharacter unit1 = Mockito.mock(ICharacter.class);
        Mockito.when(unit1.getPosition()).thenReturn(new Position(0, 0));

        ICharacter unit2 = Mockito.mock(ICharacter.class);
        Mockito.when(unit2.getPosition()).thenReturn(new Position(5, 5));

        battlefield.setUnits(List.of(unit1, unit2));

        assertEquals(2, battlefield.getUnits().size());
        assertSame(unit1, battlefield.getUnitAt(0, 0));
        assertSame(unit2, battlefield.getUnitAt(5, 5));
        assertNull(battlefield.getUnitAt(1, 1));
    }

    @Test
    void testClearRemovesAllUnits() {
        // It should remove all units from the grid and the unit list.
        ICharacter unit = Mockito.mock(ICharacter.class);
        Mockito.when(unit.getPosition()).thenReturn(new Position(0, 0));
        battlefield.setUnits(List.of(unit));

        assertFalse(battlefield.getUnits().isEmpty());

        battlefield.clear();

        assertTrue(battlefield.getUnits().isEmpty());
        assertNull(battlefield.getUnitAt(0, 0));
    }

    @Test
    void testGetUnitAtWithInvalidCoordinates() {
        // It should return null for out-of-bounds coordinates.
        assertNull(battlefield.getUnitAt(-1, 0));
        assertNull(battlefield.getUnitAt(0, -1));
        assertNull(battlefield.getUnitAt(10, 0));
        assertNull(battlefield.getUnitAt(0, 10));
    }

    @Test
    void testConstructorWithZeroSizeThrowsException() {
        // It should throw an exception if initialized with a non-positive size.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Battlefield(0);
        });
        assertEquals("Battlefield size must be positive.", exception.getMessage());
    }
}
