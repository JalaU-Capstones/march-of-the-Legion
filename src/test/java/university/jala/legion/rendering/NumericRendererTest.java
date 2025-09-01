package university.jala.legion.rendering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link NumericRenderer} class.
 */
class NumericRendererTest {

    private NumericRenderer renderer;
    private IBattlefield mockBattlefield;

    @BeforeEach
    void setUp() {
        renderer = new NumericRenderer();
        mockBattlefield = Mockito.mock(IBattlefield.class);
    }

    @Test
    void testRenderEmptyBattlefield() {
        // It should render a grid of asterisks for an empty battlefield.
        Mockito.when(mockBattlefield.getSize()).thenReturn(2);
        Mockito.when(mockBattlefield.getUnitAt(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);

        String expected = "* * \n* * \n";
        String actual = renderer.render(mockBattlefield);

        assertEquals(expected, actual);
    }

    @Test
    void testRenderBattlefieldWithUnits() {
        // It should render the correct numeric ranges for units on the battlefield.
        Mockito.when(mockBattlefield.getSize()).thenReturn(2);

        ICharacter unit1 = Mockito.mock(ICharacter.class);
        Mockito.when(unit1.getNumericRange()).thenReturn(10);

        ICharacter unit2 = Mockito.mock(ICharacter.class);
        Mockito.when(unit2.getNumericRange()).thenReturn(20);

        Mockito.when(mockBattlefield.getUnitAt(0, 0)).thenReturn(unit1);
        Mockito.when(mockBattlefield.getUnitAt(1, 1)).thenReturn(unit2);

        String expected = "10 * \n* 20 \n";
        String actual = renderer.render(mockBattlefield);

        assertEquals(expected, actual);
    }
}
