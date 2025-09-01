package university.jala.legion.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.model.interfaces.ICharacter;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link QuickSort} class.
 */
class QuickSortTest {

    private QuickSort quickSort;

    @BeforeEach
    void setUp() {
        quickSort = new QuickSort();
    }

    private ICharacter createMockUnit(int rank) {
        ICharacter unit = Mockito.mock(ICharacter.class);
        Mockito.when(unit.getRank()).thenReturn(rank);
        return unit;
    }

    @Test
    void testSortsListOfCharactersByRank() {
        // It should correctly sort the units based on their rank.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(3));
        units.add(createMockUnit(1));
        units.add(createMockUnit(2));
        units.add(createMockUnit(0));

        quickSort.sort(units);

        assertEquals(0, units.get(0).getRank());
        assertEquals(1, units.get(1).getRank());
        assertEquals(2, units.get(2).getRank());
        assertEquals(3, units.get(3).getRank());
    }

    @Test
    void testSortWithDuplicateRanks() {
        // It should handle duplicate ranks correctly.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(2));
        units.add(createMockUnit(1));
        units.add(createMockUnit(2));
        units.add(createMockUnit(0));

        quickSort.sort(units);

        assertEquals(0, units.get(0).getRank());
        assertEquals(1, units.get(1).getRank());
        assertEquals(2, units.get(2).getRank());
        assertEquals(2, units.get(3).getRank());
    }

    @Test
    void testSortWithEmptyList() {
        // It should not throw an exception for an empty list.
        List<ICharacter> units = new ArrayList<>();
        assertDoesNotThrow(() -> quickSort.sort(units));
    }

    @Test
    void testSortWithSingleElement() {
        // It should correctly handle a list with a single unit.
        List<ICharacter> units = new ArrayList<>();
        units.add(createMockUnit(5));
        quickSort.sort(units);
        assertEquals(1, units.size());
        assertEquals(5, units.get(0).getRank());
    }
}
