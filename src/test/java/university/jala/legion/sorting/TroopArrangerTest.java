package university.jala.legion.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import university.jala.legion.exception.SimulationException;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.sorting.placement.PlacementStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link TroopArranger} class.
 */
class TroopArrangerTest {

    private SortingStrategy mockSortStrategy;
    private PlacementStrategy mockPlacementStrategy;
    private TroopArranger arranger;
    private List<ICharacter> units;

    @BeforeEach
    void setUp() {
        // Create mocks for the strategies
        mockSortStrategy = mock(SortingStrategy.class);
        mockPlacementStrategy = mock(PlacementStrategy.class);

        // Use the new package-private constructor to inject the mocks
        arranger = new TroopArranger(mockSortStrategy, mockPlacementStrategy);
        units = new ArrayList<>();
    }

    @Test
    void testArrangeCallsSortThenPlace() throws SimulationException {
        // It should call the sorting strategy first, then the placement strategy.
        int battlefieldSize = 10;
        arranger.arrange(units, battlefieldSize);

        // Verify that sort() is called before place() using an InOrder verifier
        InOrder inOrder = inOrder(mockSortStrategy, mockPlacementStrategy);
        inOrder.verify(mockSortStrategy).sort(units);
        inOrder.verify(mockPlacementStrategy).place(units, battlefieldSize);
    }

    @Test
    void testArrangePropagatesSimulationException() throws SimulationException {
        // It should propagate the SimulationException thrown by the placement strategy.
        int battlefieldSize = 10;
        String errorMessage = "Test placement failure";

        // Configure the mock placement strategy to throw the exception
        doThrow(new SimulationException(errorMessage, null))
            .when(mockPlacementStrategy).place(units, battlefieldSize);

        // Assert that calling arrange results in the same exception being thrown
        assertThrows(SimulationException.class, () -> {
            arranger.arrange(units, battlefieldSize);
        });

        // Verify that the sort method was still called before the exception occurred
        verify(mockSortStrategy).sort(units);
    }
}
