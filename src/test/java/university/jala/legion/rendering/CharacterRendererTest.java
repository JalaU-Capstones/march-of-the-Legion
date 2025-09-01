package university.jala.legion.rendering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CharacterRenderer} class.
 */
class CharacterRendererTest {

    private CharacterRenderer renderer;
    private IBattlefield mockBattlefield;

    @BeforeEach
    void setUp() {
        renderer = new CharacterRenderer();
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
        // It should render the correct symbols for units on the battlefield.
        Mockito.when(mockBattlefield.getSize()).thenReturn(2);

        ICharacter unitA = Mockito.mock(ICharacter.class);
        Mockito.when(unitA.getSymbol()).thenReturn('A');

        ICharacter unitB = Mockito.mock(ICharacter.class);
        Mockito.when(unitB.getSymbol()).thenReturn('B');

        Mockito.when(mockBattlefield.getUnitAt(0, 0)).thenReturn(unitA);
        Mockito.when(mockBattlefield.getUnitAt(1, 1)).thenReturn(unitB);

        String expected = "A * \n* B \n";
        String actual = renderer.render(mockBattlefield);

        assertEquals(expected, actual);
    }
}
