package university.jala.legion.sorting;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.sorting.placement.PlacementStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link TroopArranger} class.
 */
class TroopArrangerTest {

    @Test
    void testArrangeCoordinatesSortingAndPlacement() {
        // It should call the sorting strategy first, then the placement strategy.
        SortingStrategy mockSortStrategy = Mockito.mock(SortingStrategy.class);
        PlacementStrategy mockPlacementStrategy = Mockito.mock(PlacementStrategy.class);

        // Use a custom TroopArranger to inject mocks, as the constructor uses static factories.
        TroopArranger arranger = new TroopArranger("c", 'n') {
            @Override
            public void arrange(List<ICharacter> units, int battlefieldSize) {
                mockSortStrategy.sort(units);
                mockPlacementStrategy.place(units, battlefieldSize);
            }
        };

        List<ICharacter> units = new ArrayList<>();
        int battlefieldSize = 10;

        arranger.arrange(units, battlefieldSize);

        // Verify that sort() is called before place()
        InOrder inOrder = inOrder(mockSortStrategy, mockPlacementStrategy);
        inOrder.verify(mockSortStrategy).sort(units);
        inOrder.verify(mockPlacementStrategy).place(units, battlefieldSize);
    }
}
